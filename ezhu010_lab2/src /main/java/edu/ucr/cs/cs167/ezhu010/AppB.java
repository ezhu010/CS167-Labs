package edu.ucr.cs.cs167.ezhu010;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Options;
import java.util.Random;




public class AppB
{
    public static void main( String[] args ) throws IOException
    {
        Configuration conf = new Configuration();
        if(args.length != 1){
            System.out.println("Error, need  1 command line arguments");
            System.exit(0);
        }
        Path input_path = new Path(args[0]);

        FileSystem fs_input = input_path.getFileSystem(conf);
        if(!fs_input.exists(input_path)){
            System.out.println("File " + args[0] + " not found!");
            System.exit(0);
        }
        long startTime = System.nanoTime();
        FSDataInputStream in_file = fs_input.open(input_path);
        byte[] b = new byte[8192];
        Random randomNum = new Random();
        for(int i = 0; i < 10000; i++){
            in_file.seek(randomNum.nextInt(Integer.MAX_VALUE));
            in_file.read(b);
        }

        in_file.close();
        long endTime = System.nanoTime();
        long totalTime  = endTime - startTime;
        System.out.println(totalTime / 10e8 + " seconds to read " + args[0]);


    }
}
