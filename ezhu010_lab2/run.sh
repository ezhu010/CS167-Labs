#!/usr/bin/env sh
mvn clean package
# LAB 2
#hadoop jar target/ezhu010_lab2-1.0-SNAPSHOT.jar inputs.txt outputs.txt
time cp AREAWATER.csv outputs.txt
hadoop jar target/ezhu010_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.App file://`pwd`/AREAWATER.csv  hdfs:///test/AREAWATER.csv
hadoop jar target/ezhu010_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.App file://`pwd`/AREAWATER.csv  file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab2/outputs.txt
hadoop jar target/ezhu010_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.App hdfs:///test/AREAWATER.csv  file://`pwd`/AREAWATER.csv
hadoop jar target/ezhu010_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.App hdfs:///test/AREAWATER.csv hdfs:///test/test.csv


#LAB 2 BONUS
hadoop jar target/ezhu010_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.AppB file://`pwd`/AREAWATER.csv
hadoop jar target/ezhu010_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.AppB hdfs:///test/AREAWATER.csv

