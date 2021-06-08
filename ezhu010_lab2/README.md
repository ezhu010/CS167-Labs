# Lab 2

## Student information
* Full name: Edward Zhu
* E-mail: ezhu010@ucr.edu
* UCR NetID: ezhu010
* Student ID: 862174709

## Answers

(Q1)  Verify the file size and record the running time.

    File size:  2271210910
    Running Time:  8.07647399

(Q2)  Record the running time of the copy command.

    Running Time: 
        real	0m3.381s
        user	0m0.005s
        sys	        0m1.228s

(Q3)  How do the two numbers compare? (The running times of copying the file through your program and the operating system.) Explain in your own words why you see these results.

    The running time of copying the file thorugh my operating system is much faster
    than copying the file through the my program. The reason is because there is a lot of 
    overhead in my program. For example, the hdfs writing process requires you to
    allocate space and open the file everytime, which adds time. 

(Q4) Does the program run after you change the default file system to HDFS? What is the error message, if any, that you get?

    My program does not run and the reason is becuase the file AREAWATER.csv 
    was not found. This happens because we are not using hdfs and AREAWATER.csv 
    was not copied to hdfs. 

(Q5) Use your program to test the following cases and record the running time for each case
    
    local -> local 
    Copied 2271210910 bytes from file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab2/
    AREAWATER.csv to file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab2/outputs.txt 
    in 5.865375102 seconds

    i) local file system -> HDFS
        time hdfs dfs -cp file:///Users/edwardzhu/AREAWATER.csv hdfs:///user/edwardzhu/ 
        real	0m5.648s
        user	0m9.313s
        sys	        0m2.867s

        Copied 2271210910 bytes from file:///Users/edwardzhu/Desktop/
        workspace/ezhu010_lab2/AREAWATER.csv to hdfs:///test/AREAWATER.csv in 
        10.384136045 seconds


    ii) HDFS -> local file system
        time hdfs dfs -cp hdfs:///user/edwardzhu/AREAWATER.csv file:///Users/edwardzhu/Desktop
        real	0m9.989s
        user	0m6.690s
        sys	        0m4.005s

        Copied 2271210910 bytes from hdfs:///test/AREAWATER.csv to 
        file:///Users/edwardzhu/Desktop/workspace/ezhu010_lab2/AREAWATER.csv 
        in 12.809032773 seconds


    iii) HDFS -> HDFS 
        time hdfs dfs -cp hdfs:///user/edwardzhu/AREAWATER.csv hdfs:///user/edwardzhu/test
        real	0m5.949s
        user	0m10.634s
        sys	        0m2.184s

        Copied 2271210910 bytes from hdfs:///test/AREAWATER.csv to 
        hdfs:///test/test.csv in 13.152864595 seconds




|         | Time|| 
| ------------- |:-------------:| -----:|
| Copy with Program      | 5.865375102 | 
| Copy with terminal     |    3.381s   
| Local -> HDFS |  10.384136045 seconds
| HDFS -> Local  | 12.809032773 seconds 
| HDFS -> HDFS   | 13.152864595 seconds | 

(Q6)

Test your program on two files, one file stored on the local file system, 
and another file stored on HDFS. Compare the running times of both tasks. What do you observe?

    Local File System: 6.530013863 seconds to read file:///Users/edwardzhu/Desktop/
    workspace/ezhu010_lab2/AREAWATER.csv

    HDFS: 12.213915964 seconds to read hdfs:///test/AREAWATER.csv
    Reading from HDFS takes much longer than reading from the local file system.
        
