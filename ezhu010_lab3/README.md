# Lab 3

## Student information
* Full name: Edward Zhu
* E-mail: ezhu010@ucr.edu
* UCR NetID: ezhu010
* Student ID: 862174709

## Answers

(Q1) What do you think the line job.setJarByClass(Filter.class); does?

    I think that the line "job.setJarByClass(Filter.class);" Collects the JAR file 
    that contains the user-defined functions and is part of the job preparation. It tells
    hadoop which jar file will be executed. 

(Q2) What is the effect of the line job.setNumReduceTasks(0);?

    When you set the reduce tasks to zero you are skipping the shuffle phase and going
    map output is written directly to HDFS as the final answer.

(Q3) Where does the main function run? (Driver node, Master node, or an execturo node).
    
    Driver Node

(Q4) How many lines do you see in the output?

    27972

(Q5) How many files are produced in the output? 

    1 file in the output 

(Q6) Explain this number based on the input file size and default block size.

    This number is 1 because it is less than our default block size, which should be 32

(Q7) How many files are produced in the output?

    1 file 

(Q8) Explain this number based on the input file size and default block size.
    
    HDFS has a default block size of 128 MB and our sample input file has a
    size of 2.2 MB

(Q9) How many files are produced in the output directory and how many lines are there in each file?

    There is two file:
    "part-r-00000" and it has 4 lines: 
    200	481974462
    302	26005
    304	0
    404	0
    "part-2-00001" has no lines.

(Q10) Explain these numbers based on the number of reducers and number of response codes in the input file.

    The code for Aggregation has set the number of reduce tasks to two. That means two 
    files will be in the output. The number after the 200/302/304/404 is the number of 
    bytes that correspond to the byte code. For example every number that has a response 
    code of 304 is 0 byte. 

(Q11) How many files are produced in the output directory and how many lines are there in each file? 

    Two files were produced  in the output and one file has 5 lines, 
    the other has two lines.

(Q12) Explain these numbers based on the number of reducers and number of response codes in the input file.

    Again, since the number of reduce task is set to two, there will be two files. 
    In the nasa_19950630.22-19950728.12.tsv there are 7 response code in total so thats
    why the output has 7 lines total. Similarily the number on the right of each reponse
    code is the sum of all the bytes that correspond to the code. 


(Q13) How many files are produced in the output directory and how many lines are there in each file?

    There are two files in the output file and part-r-00000 has one line and 
    part-r-00001 has no lines. 

(Q14) Explain these numbers based on the number of reducers and number of response codes in the input file.

    Again, since the number of reduce task is set to two, there will be two files.
    When I ran the Filter class with the input file, the output file only contained
    response code of 200. Then when I ran the Aggregation class on the output of filter
    it only outputted one line because there was only one reponse code in input (200) 

