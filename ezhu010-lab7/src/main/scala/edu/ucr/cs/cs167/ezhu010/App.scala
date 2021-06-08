package edu.ucr.cs.cs167.ezhu010
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.ml.classification.{LogisticRegression, LogisticRegressionModel}
import org.apache.spark.ml.evaluation.{BinaryClassificationEvaluator, RegressionEvaluator}
import org.apache.spark.ml.feature.{HashingTF, StringIndexer, Tokenizer, VectorAssembler, Word2Vec}
import org.apache.spark.ml.regression.{LinearRegression, LinearRegressionModel}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.ml.tuning.{CrossValidator, ParamGridBuilder, TrainValidationSplit}

object App {

  def main(args : Array[String]) {
    if (args.length < 2) {
      println("Usage <input file> <algorithm>")
      println("<input file> path to a CSV file input")
      println("<algorithm> is either regression or classification")
    }
    val inputfile = args(0)
    val method = args(1)
    val conf = new SparkConf
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    println(s"Using Spark master '${conf.get("spark.master")}'")

    val spark = SparkSession
      .builder()
      .appName("CS167 Lab9")
      .config(conf)
      .getOrCreate()

    val t1 = System.nanoTime
    try {
      if (method.equals("regression"))
      {
        // TODO apply regression model on house prices
        val inputFile = spark.read.format("csv")
          .option("sep", ",")
          .option("inferSchema", "true")
          .option("header", "true")
          .load(inputfile)

//      val allFeatures = Array(
//        Array("bedrooms", "bathrooms", "sqft_living", "sqft_lot"),
//        Array("bedrooms", "bathrooms", "sqft_living", "sqft_lot", "condition", "waterfront", "view"),
//        Array("sqft_living", "sqft_lot", "sqft_above", "sqft_basement", "sqft_living15", "sqft_lot15"),
//        Array("bedrooms", "bathrooms", "condition", "waterfront", "view", "sqft_living", "sqft_lot", "sqft_above", "sqft_basement", "sqft_living15", "sqft_lot15"),
//
//      )
        val vecAssembler = new VectorAssembler().setInputCols(Array("bedrooms", "bathrooms", "sqft_living", "sqft_lot")).setOutputCol("features")
//        val vecAssembler = new VectorAssembler().setOutputCol("features")


        val linRegress = new LinearRegression().setFeaturesCol("features").setLabelCol("price")

        val pipeline = new Pipeline().setStages(Array(vecAssembler, linRegress))

        val grid = new ParamGridBuilder().addGrid(linRegress.regParam, Array(0.01, 0.1, 0.3)).addGrid(linRegress.elasticNetParam, Array(0.0, 0.3, 0.8, 1.0)).build()
//        val grid = new ParamGridBuilder().addGrid(linRegress.regParam, Array(0.01, 0.1, 0.3)).addGrid(linRegress.elasticNetParam, Array(0.0, 0.3, 0.8, 1.0)).addGrid(vecAssembler.inputCols, allFeatures).build()

        val cross = new CrossValidator().setEstimator(pipeline).setEvaluator(new RegressionEvaluator().setLabelCol("price")).setEstimatorParamMaps(grid).setNumFolds(5).setParallelism(2)

        val Array(trainingData, testData) = inputFile.randomSplit(Array(0.8, 0.2))

        val model = cross.fit(trainingData)
        val chosenModel = model.bestModel.asInstanceOf[PipelineModel].stages(1).asInstanceOf[LinearRegressionModel]
        println(s"ElasticNetParam: ${chosenModel.getElasticNetParam}")
        println(s"regParam: ${chosenModel.getRegParam}")
        println(s"coefficients: ${chosenModel.coefficients}")

//        val chosenFeatures = model.bestModel.asInstanceOf[PipelineModel].stages(0).asInstanceOf[VectorAssembler].getInputCols.mkString(",")
//        println(s"Chosen features: ${chosenFeatures}")

        val predictions = model.transform(testData)
        predictions.select("price", "prediction").show(5)

        val rmse = new RegressionEvaluator()
          .setLabelCol("price")
          .setPredictionCol("prediction")
          .setMetricName("rmse")
          .evaluate(predictions)
        println(s"RMSE on test set is $rmse")

        val corr = Correlation.corr(new VectorAssembler()
          .setInputCols(Array("prediction", "price"))
          .setOutputCol("features2")
          .transform(predictions), "features2").head
        println(s"Correlation is $corr")


        //        val model = linRegress.fit(vecAssembler.transform(inputFile))
//        println(model.coefficients)

      } else if (method.equals("classification")) {
        // TODO process the sentiment data

        val input = spark.read.format("csv").option("header", "true").option("quote", "\"").option("escape","\"").load(inputfile)
        input.printSchema(5)
        input.show(5)

        val tokenizer  = new Tokenizer().setInputCol("text").setOutputCol("words")
        val hashingTF = new HashingTF().setInputCol("words").setOutputCol("features")
        val stringIndexer = new StringIndexer().setInputCol("sentiment").setOutputCol("label").setHandleInvalid("skip")
        val logRegress = new LogisticRegression()

        val pipeline = new Pipeline().setStages(Array(tokenizer,hashingTF,stringIndexer,logRegress))

        val grid = new ParamGridBuilder().addGrid(hashingTF.numFeatures, Array(10, 100, 1000)).addGrid(logRegress.regParam ,Array(0.01,0.1,0.3,0.8)).build()

        val cv = new TrainValidationSplit()
          .setEstimator(pipeline)
          .setEvaluator(new BinaryClassificationEvaluator())
          .setEstimatorParamMaps(grid)
          .setTrainRatio(0.8)
          .setParallelism(2)

        val Array(trainingData, testData) = input.randomSplit(Array(0.8, 0.2))

        val logisticModel = cv.fit(trainingData)

        val numFeatures = logisticModel.bestModel.asInstanceOf[PipelineModel].stages(1).asInstanceOf[HashingTF].getNumFeatures
        val regParam = logisticModel.bestModel.asInstanceOf[PipelineModel].stages(3).asInstanceOf[LogisticRegressionModel].getRegParam

        val predictions = logisticModel.transform(testData)
        predictions.select("text", "label", "prediction", "probability").show()

        val binaryClassificationEvaluator = new BinaryClassificationEvaluator()
          .setLabelCol("label")
          .setRawPredictionCol("prediction")

        val accuracy = binaryClassificationEvaluator.evaluate(predictions)
        println(s"Accuracy of the test set is $accuracy")


      } else {
        println(s"Unknown algorithm ${method}")
        System.exit(1)
      }
      val t2 = System.nanoTime
      println(s"Applied algorithm $method on input $inputfile in ${(t2 - t1) * 1E-9} seconds")
    } finally {
      spark.stop
    }
  }
}
