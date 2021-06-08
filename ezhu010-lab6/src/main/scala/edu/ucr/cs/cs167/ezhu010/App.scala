package edu.ucr.cs.cs167.ezhu010

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf

object App {

  def main(args : Array[String]) {
    val command: String = args(0)
    val inputfile: String = args(1)
    val conf = new SparkConf
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    println(s"Using Spark master '${conf.get("spark.master")}'")

    val spark = SparkSession
      .builder()
      .appName("CS167 Lab6")
      .config(conf)
      .getOrCreate()

    try {
      val input = spark.read.format("csv")
        .option("sep", "\t")
        .option("inferSchema", "true")
        .option("header", "true")
        .load(inputfile)

      import spark.implicits._

//      input.show()
//      input.printSchema()
      input.createOrReplaceTempView("log_lines")



      val t1 = System.nanoTime
      command match {
        case "count-all" =>
          val countRecords = input.count()
          println(s"Total count for file '${inputfile}' is $countRecords")

        // TODO count total number of records in the file
        case "code-filter" =>
        // TODO Filter the file by response code, args(2), and print the total number of matching lines
          val responseCode: String = args(2)

        val responseCount = input.filter($"response" === responseCode.toInt).count()
          println(s"Total count for file '${inputfile}' with response code ${args(2)} is $responseCount")

        case "time-filter" =>
        // TODO Filter by time range [from = args(2), to = args(3)], and print the total number of matching lines
          val countTime = input.filter($"time".between(args(2).toInt, args(3).toInt)).count()
          println(s"Total count for file '${inputfile}' in time range [${args(2)}, ${args(3)}] is $countTime")

        case "count-by-code" =>
        // TODO Group the lines by response code and count the number of records per group
          println(s"Number of lines per code for the file ${inputfile}")
          input.groupBy($"response").count().show()
        case "sum-bytes-by-code" =>
        // TODO Group the lines by response code and sum the total bytes per group
          println(s"Total bytes per code for the file ${inputfile}")
          input.groupBy("response").sum("bytes").show()
        case "avg-bytes-by-code" =>
        // TODO Group the liens by response code and calculate the average bytes per group
          println(s"Average bytes per code for the file ${inputfile}")
          input.groupBy("response").avg("bytes").show()
        case "top-host" =>
        // TODO print the host the largest number of lines and print the number of lines
//          val host = input.groupBy("host").count().orderBy($"count".desc).first()
//          println(s"Top host in the file ${inputfile} by number of entries")
//          println(s"Host: ${host.getAs[String]("host")}")
//          println(s"Number of entries: ${host.getAs[Int]("count")}")
          val host = spark.sql("SELECT host, count(*) AS count FROM log_lines GROUP BY host ORDER BY count DESC LIMIT 1").first().getAs[String]("host")
          val count = spark.sql("SELECT host, count(*) AS count FROM log_lines GROUP BY host ORDER BY count DESC LIMIT 1").first().getAs[Long]("count")
          println(s"Top host in the file ${inputfile} by number of entries")
          println(s"Host: ${host}")
          println(s"Number of entries: ${count}")
        case "comparison" =>
        // TODO Given a specific time, calculate the number of lines per response code for the
        // entries that happened before that time, and once more for the lines that happened at or after
        // that time. Print them side-by-side in a tabular form.
          val beforeTime = input.filter($"time" < args(2).toInt).groupBy("response").count().withColumnRenamed("count", "count_before")
          val afterTime = input.filter($"time" >= args(2).toInt).groupBy("response").count().withColumnRenamed("count", "count_after")
          println(s"Comparison of the number of lines per code before and after ${args(2)} on file ${inputfile}")
          beforeTime.join(afterTime, "response").show()

      }
      val t2 = System.nanoTime
      println(s"Command '${command}' on file '${inputfile}' finished in ${(t2-t1)*1E-9} seconds")


    } finally {
      spark.stop
    }
  }
}


