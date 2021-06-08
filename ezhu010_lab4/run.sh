mvn clean package
#spark-submit --class edu.ucr.cs.cs167.ezhu010.Aggregation --master local[2] target/ezhu010_lab4-1.0-SNAPSHOT.jar nasa_19950801.tsv aggregation_output
#spark-submit --class edu.ucr.cs.cs167.ezhu010.Filter --master local[2] target/ezhu010_lab4-1.0-SNAPSHOT.jar nasa_19950630.22-19950728.12.tsv filter_output 200
spark-submit --class edu.ucr.cs.cs167.ezhu010.Filter --master local[2] target/ezhu010_lab4-1.0-SNAPSHOT.jar nasa_19950630.22-19950728.12.tsv filter_output 302
spark-submit --class edu.ucr.cs.cs167.ezhu010.Aggregation --master local[2] target/ezhu010_lab4-1.0-SNAPSHOT.jar filter_output aggregation_output
