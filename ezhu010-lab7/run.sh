mvn clean package
spark-submit --class edu.ucr.cs.cs167.ezhu010.App --master local[2] target/ezhu010-lab7-1.0-SNAPSHOT.jar kc_house_data.csv regression
#spark-submit --class edu.ucr.cs.cs167.ezhu010.App --master local[2] target/ezhu010-lab7-1.0-SNAPSHOT.jar sentiment.csv classification
