# Lab 4

## Student information
* Full name: Edward Zhu
* E-mail: ezhu010@ucr.edu
* UCR NetID: ezhu010
* Student ID: 862174709

## Answers

(Q1) Do you think it will use your local cluster? Why or why not?

    No, I do not think it will use my local cluster because nothing changed on the web
    interface. 


(Q2) Does the application use the cluster that you started? How did you find out?

    Yes the application does use the cluster because on the web interface I see that
    there was a completed application. 

(Q3) What is the Spark master printed on the standard output on IntelliJ IDEA?

    Using Spark master 'local[*]'

(Q4) What is the Spark master printed on the standard output on the terminal?

    Using Spark master 'local[*]'

(Q5) For the previous command that prints the number of matching lines, list all the processed input splits.

    HadoopRDD: Input split: file:/Users/edwardzhu/Desktop/workspace/ezhu010_lab4/nasa_19950801.tsv:0+1169610
    HadoopRDD: Input split: file:/Users/edwardzhu/Desktop/workspace/ezhu010_lab4/nasa_19950801.tsv:1169610+1169610

(Q6) For the previous command that counts the lines and prints the output, how many splits were generated?

    4 splits were generated 

    HadoopRDD: Input split: file:/Users/edwardzhu/Desktop/workspace/ezhu010_lab4/nasa_19950801.tsv:1169610+1169610
    HadoopRDD: Input split: file:/Users/edwardzhu/Desktop/workspace/ezhu010_lab4/nasa_19950801.tsv:0+1169610
    HadoopRDD: Input split: file:/Users/edwardzhu/Desktop/workspace/ezhu010_lab4/nasa_19950801.tsv:1169610+1169610
    HadoopRDD: Input split: file:/Users/edwardzhu/Desktop/workspace/ezhu010_lab4/nasa_19950801.tsv:0+1169610

(Q7) Compare this number to the one you got earlier. 

    It is double the amount of splits we got earlier.

(Q8) Explain why we get these numbers.

    Since the new code contained another action (saveAsTextFile) we needed to read 
    the input file again. Before we only had only action (count) so with two actions 
    there will be double the amount of splits. 

(Q9) What can you do to the current code to ensure that the file is read only once?
    
    Use the cache function on matchingLines and the result is that we only get two splits

