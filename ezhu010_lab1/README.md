# Lab 1

## Student information
* Full name: Edward Zhu 
* E-mail: ezhu010@ucr.edu
* UCR NetID: ezhu010
* Student ID: 862174709

## Answers

* (Q1) What is the name of the created directory?
  

    ezhu010_lab1
  

* (Q2) What do you see at the console output? 


    Hello World!


* (Q3) What do you see at the output? 


    log4j:WARN No appenders could be found for logger (org.apache.hadoop.metrics2.lib.MutableMetricsFactory).
    log4j:WARN Please initialize the log4j system properly.
    log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
    WARNING: An illegal reflective access operation has occurred
    WARNING: Illegal reflective access by org.apache.hadoop.security.authentication.util.KerberosUtil (file:/Users/edwardzhu/.m2/repository/org/apache/hadoop/hadoop-auth/2.10.0/hadoop-auth-2.10.0.jar) to method sun.security.krb5.Config.getInstance()
    WARNING: Please consider reporting this to the maintainers of org.apache.hadoop.security.authentication.util.KerberosUtil
    WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
    WARNING: All illegal access operations will be denied in a future release
    Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
    at edu.ucr.cs.cs167.ezhu010.App.main(App.java:63)
    The output.txt folder is not created because an input/output was not specified. 




* (Q4) What is the output that you see at the console?
  
    
    log4j:WARN No appenders could be found for logger (org.apache.hadoop.metrics2.lib.MutableMetricsFactory).
    log4j:WARN Please initialize the log4j system properly.
    log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
    This created an output.txt folder that contained the count of all the words in input.txt.
m

* (Q5) Does it run? Why or why not? 
  

    When I run java -cp target/ezhu010_lab1-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.App
    it does not run. This is because we added hadoop in to our java code and in order for it
    to run we need to use the hadoop command. 
    hadoop jar target/ezhu010_lab1-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.ezhu010.App input.txt output.txt
    Also if you run the command again if the output.txt already exists it will tell
    you that an output.txt already exists and it won't run. But we run mvn clean package
    it will work fine.