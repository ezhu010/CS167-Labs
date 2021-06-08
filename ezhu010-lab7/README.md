# Lab 7 and 8 

## Student information
* Full name: Edward Zhu
* E-mail: ezhu010@ucr.edu
* UCR NetID: ezhu010
* Student ID: 862174709

## Answers

(Q1) In the README file, explain how you did it and include a code snippet that highlights the code that you added to make it work.

      val allFeatures = Array(
        Array("bedrooms", "bathrooms", "sqft_living", "sqft_lot"),
        Array("bedrooms", "bathrooms", "sqft_living", "sqft_lot", "condition", "waterfront", "view"),
        Array("sqft_living", "sqft_lot", "sqft_above", "sqft_basement", "sqft_living15", "sqft_lot15"),
        Array("bedrooms", "bathrooms", "condition", "waterfront", "view", "sqft_living", "sqft_lot", "sqft_above", "sqft_basement", "sqft_living15", "sqft_lot15"),

      )

        val vecAssembler = new VectorAssembler().setOutputCol("features")
        val grid = new ParamGridBuilder().addGrid(linRegress.regParam, Array(0.01, 0.1, 0.3)).addGrid(linRegress.elasticNetParam, Array(0.0, 0.3, 0.8, 1.0)).addGrid(vecAssembler.inputCols, allFeatures).build()
        val chosenFeatures = model.bestModel.asInstanceOf[PipelineModel].stages(0).asInstanceOf[VectorAssembler].getInputCols.mkString(",")
        println(s"Chosen features: ${chosenFeatures}")

(Q2) Which one provided the best model for you?

    Chosen features: bedrooms,bathrooms,condition,waterfront,view,sqft_living,sqft_lot,sqft_above,sqft_basement,sqft_living15,sqft_lot15




