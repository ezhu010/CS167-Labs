# Lab 5

## Student information
* Full name: Edward Zhu
* E-mail: ezhu010@ucr.edu
* UCR NetID: ezhu010
* Student ID: 862174709

## Answers

(Q1) What are these two arguments?

    The first arguement is the command which, can be a transformation or action. 
    The second arguement is the path to the input file. 


(Bonus) Explain your method in the README file and add the code snippet that performs this task. Mark your answer with (B). Hint, check the aggregateByKey function.


        tempRDD.map(line => (line.split("\t")(5), (line.split("\t")(6).toLong, 1.0))).aggregateByKey((0.0, 0.0))(
        (x, y) => (x._1 + y._1, x._2 + y._2),
        (x1, y1) => (x1._1 + y1._1, x1._2 + y1._2)).mapValues(sumCount => sumCount._1 / sumCount._2).collect().foreach(x => println(x._1 + "," + x._2))

        Explaination:
        I first mapped the value to be a tuple(bytes, 1.0) and set the tuple in aggregateByKey to 0.0, 0.0
        because initally there are 0 byte and count. Then I added the bytes and count seperaely and in the
        end I divided the total bytes / count corresponding to that reponse count. 