package edu.ucr.cs.cs167.ezhu010
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object App {

  def main(args : Array[String]) {
    val command: String = args(0)
    val inputfile: String = args(1)

    val conf = new SparkConf
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    println(s"Using Spark master '${conf.get("spark.master")}'")
    conf.setAppName("lab5")
    val sparkContext = new SparkContext(conf)
    try {
      val inputRDD: RDD[String] = sparkContext.textFile(inputfile)
      // TODO Parse the input file using the tab separator and skip the first line
      val tempRDD: RDD[String] = inputRDD.filter(line => !line.startsWith("host\tlogname"))

      val mainRDD: RDD[Array[String]] = tempRDD.map(line => line.split("\t"))

      val t1 = System.nanoTime
      command match {
      case "count-all" =>
        val totalRecords = mainRDD.count();
        println(s"Total count for file '${inputfile}' is $totalRecords ")
      // TODO count total number of records in the file
      case "code-filter" =>
        val responseCode = args(2);
        val codeFilter = tempRDD.filter(tempRDD => tempRDD.split("\t")(5).equals(args(2))).count()
        println(s"Total count for file '${inputfile}' with response code ${args(2)} is $codeFilter")
      // TODO Filter the file by response code, args(2), and print the total number of matching lines
      case "time-filter" =>
        val timeCount = tempRDD.filter(line => line.split("\t")(2).toLong >= args(2).toLong && line.split("\t")(2).toLong <= args(3).toLong).count()
        println(s"Total count for file '${inputfile}' in time range [${args(2)}, ${args(3)}] is $timeCount")
      // TODO Filter by time range [from = args(2), to = args(3)], and print the total number of matching lines
      case "count-by-code" =>
        println(s"Number of lines per code for the file ${inputfile}")
        println("Code,Count")
        tempRDD.map(line => (line.split("\t")(5), 1)).countByKey().foreach(count => println(count._1 + "," + count._2))
      // TODO Group the lines by response code and count the number of records per group
      case "sum-bytes-by-code" =>
        println(s"Total bytes per code for the file ${inputfile}")
        println("Code,Sum(bytes)")
        tempRDD.map(line => (line.split("\t")(5), line.split("\t")(6).toLong)).reduceByKey((a, b) => a + b).collect().foreach(temp => println(temp._1 + "," + temp._2))
          // TODO Group the lines by response code and sum the total bytes per group
      case "avg-bytes-by-code" =>
        println(s"Average bytes per code for the file ${inputfile}")
        println("Code,Avg(bytes)")
        val avgCount = tempRDD.map(line => (line.split("\t")(5), 1)).countByKey()
        tempRDD.map(line => (line.split("\t")(5), line.split("\t")(6).toLong)).reduceByKey((a, b) => a + b).map(temp => (temp._1, temp._2 / avgCount(temp._1).toDouble)).foreach(temp => println(temp._1 + "," + temp._2))
          // TODO Group the liens by response code and calculate the average bytes per group
          // B
          //        tempRDD.map(line => (line.split("\t")(5), (line.split("\t")(6).toLong, 1.0))).aggregateByKey((0.0, 0.0))(
          //          (x, y) => (x._1 + y._1, x._2 + y._2),
          //         (x1, y1) => (x1._1 + y1._1, x1._2 + y1._2)).mapValues(sumCount => sumCount._1 / sumCount._2).collect().foreach(x => println(x._1 + "," + x._2))


      case "top-host" =>
        println(s"Top host in the file ${inputfile} by number of entries")
        val output = tempRDD.map(line => (line.split("\t")(0), 1)).reduceByKey((a, b) => a + b).sortBy(temp => temp._2, false).first()
        println(s"Host: ${output._1}")
        println(s"Number of entries: ${output._2}")
          // TODO print the host the largest number of lines and print the number of lines
      case "comparison" =>
          // TODO Given a specific time, calculate the number of lines per response code for the
          // entries that happened before that time, and once more for the lines that happened at or after
          // that time. Print them side-by-side in a tabular form.
        println(s"Comparison of the number of lines per code before and after ${args(2)} on file ${inputfile}")
        println(s"Code,Count before,Count after")
        val beforeTime = tempRDD.filter(line => line.split("\t")(2) < args(2)).map(line => (line.split("\t")(5), 1)).countByKey()
        tempRDD.filter(line => line.split("\t")(2) >= args(2)).map(line => (line.split("\t")(5), 1)).countByKey().foreach(temp => println(temp._1 + "," + beforeTime(temp._1) + "," + temp._2))
      }
      val t2 = System.nanoTime
      println(s"Command '${command}' on file '${inputfile}' finished in ${(t2-t1)*1E-9} seconds")
    } finally {
      sparkContext.stop
    }
  }
}