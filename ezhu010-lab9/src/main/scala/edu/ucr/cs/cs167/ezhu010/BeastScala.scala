
package edu.ucr.cs.cs167.ezhu010

import edu.ucr.cs.bdlab.beast.cg.SpatialJoinAlgorithms.{ESJDistributedAlgorithm, ESJPredicate}
import edu.ucr.cs.bdlab.beast.geolite.{Feature, IFeature}
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.json4s.DefaultFormats.dateFormat.timezone
import org.locationtech.jts.geom.{Envelope, GeometryFactory}

import java.text.SimpleDateFormat
import java.util.{Calendar, Date, TimeZone}

object BeastScala {
  def main(args: Array[String]): Unit = {
    // Initialize Spark context

    val conf = new SparkConf().setAppName("Beast Example")
    // Set Spark master to local if not already set
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")

    val spark: SparkSession.Builder = SparkSession.builder().config(conf)

    val sparkSession = spark.getOrCreate()
    val sparkContext = sparkSession.sparkContext

    try {
      import edu.ucr.cs.bdlab.beast._
      val tweets = sparkContext.readCSVPoint("tweets.csv", delimiter = '\t', skipHeader = true)
      val timezones = sparkContext.shapefile("ne_10m_time_zones.zip")
      val counties = sparkContext.shapefile("tl_2018_us_county.zip")


      val donutTweets = tweets.filter(feature => feature.getAs[String](2).contains("donut"))
      val donutTweetsCounties = counties.spatialJoin(donutTweets)
      val donutTweetsByCounties = donutTweetsCounties.countByKey()
      val sortedTweets = donutTweetsByCounties.toArray.sortBy(_._2)(Ordering[Long].reverse).take(20)
      val top_counties = sparkContext.parallelize(sortedTweets.map(tweet => tweet._1), 1)
      top_counties.plotImage(2000,2000, "counties_donut.png")


//      val bagelTweets = tweets.filter(feature => feature.getAs[String](2).contains("bagel"))
//      val bagelTweetsCounties = counties.spatialJoin(bagelTweets)
//      val bagelTweetsByCounties = bagelTweetsCounties.countByKey()
//      val sortedTweets = bagelTweetsByCounties.toArray.sortBy(_._2)(Ordering[Long].reverse).take(20)
//      val top_counties = sparkContext.parallelize(sortedTweets.map(tweet => tweet._1), 1)
//      top_counties.plotImage(2000,2000, "counties_bagel.png")

//      val potatoTweets = tweets.filter(feature => feature.getAs[String](2).contains("potato"))
//      val potatoTweetsCounties = counties.spatialJoin(potatoTweets)
//      val potatoTweetsByCounties = potatoTweetsCounties.countByKey()
//      val sortedTweets = potatoTweetsByCounties.toArray.sortBy(_._2)(Ordering[Long].reverse).take(20)
//      val top_counties = sparkContext.parallelize(sortedTweets.map(tweet => tweet._1), 1)
////      top_counties.plotImage(2000,2000, "counties_potato.png")
//
      top_counties.plotPyramid("counties_donut", 12, opts = "mercator" -> true)





      //      val coffeeTweets = tweets.filter(feature => fe      val coffeeTweets = tweets.filter(feature => feature.getAs[String](2).contains("coffee"))
      ////      val coffeeTweetsTimeZone: RDD[(IFeature, IFeature)] = coffeeTweets.spatialJoin(timezones)
      ////      val dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")ature.getAs[String](2).contains("coffee"))
//      val coffeeTweetsTimeZone: RDD[(IFeature, IFeature)] = coffeeTweets.spatialJoin(timezones)
//      val dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

//      val coffeeTweetsByHour = coffeeTweetsTimeZone.map( tweet =>  {
//        val time: Date = dateParser.parse(tweet._1.getAs[String](1))
//        val cal : Calendar = Calendar.getInstance(TimeZone.getTimeZone(tweet._2.getAs[String]("time_zone")))
//        cal.setTime(time)
//        (cal.get(Calendar.HOUR_OF_DAY) , 1)
//      }
//
//      ).countByKey()

//      for (h <- 0 to 23) {
//        print(coffeeTweetsByHour.getOrElse(h, 0))
//        if (h != 23)
//          print(", ")
//      }
//      println


      // Write the output in CSV format
    } finally {
      sparkSession.stop()
    }
  }
}

