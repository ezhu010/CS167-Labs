mvn clean package
#hadoop jar target/ezhu010_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.Filter file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab3/sample2.tsv file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab3/filter_output.tsv 200
#yarn jar target/ezhu010_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.Filter file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab3/nasa_19950801.tsv filter_output.tsv 200
#yarn jar target/ezhu010_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.Aggregation file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab3/nasa_19950801.tsv file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab3/aggregation_output.tsv
#yarn jar target/ezhu010_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.Aggregation file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab3/nasa_19950630.22-19950728.12.tsv file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab3/aggregation_output.tsv
yarn jar target/ezhu010_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.Filter file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab3/nasa_19950801.tsv file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab3/filter_output.tsv 200
yarn jar target/ezhu010_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.Aggregation file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab3/filter_output.tsv/part-m-00000 file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab3/aggregation_output.tsv



