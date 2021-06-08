package edu.ucr.cs.cs167.ezhu010;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.SparkConf;
import scala.Int;
import scala.Tuple2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
import java.io.FileWriter;



public class Aggregation  {
    public static void main( String[] args ) throws IOException {
        final String inputfile = args[0];
        final String outputfile = args[1];
        BufferedWriter finalOutput = new BufferedWriter(new FileWriter(outputfile));
        SparkConf conf = new SparkConf();
        if (!conf.contains("spark.master"))
            conf.setMaster("local[*]");
        System.out.printf("Using Spark master '%s'\n", conf.get("spark.master"));
        conf.setAppName("lab4");
        JavaSparkContext spark = new JavaSparkContext(conf);


        try {
            JavaRDD<String> logFile = spark.textFile(inputfile);
            JavaPairRDD<String, Integer> linesByCode = logFile.mapToPair(line -> new Tuple2<String, Integer>(line.split("\t")[5], 1));
            Map<String, Long> count = linesByCode.countByKey();
            for(Map.Entry<String, Long> temp : count.entrySet()) {
                System.out.println("Code '" + temp.getKey() + "' : number of entries " + temp.getValue() + "\n");

                finalOutput.write("Code '" + temp.getKey() + "' : number of entries " + temp.getValue() + "\n");
            }
//            System.out.printf("Number of lines in the log file %d\n", logFile.count());
        } finally {
            finalOutput.close();
            spark.close();
        }
    }
}