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
import java.net.URI;



public class App
{
    public static void main( String[] args ) throws IOException
    {
        Configuration conf = new Configuration();
        //conf.addResource(new Path("/Users/edwardzhu/hadoop-2.10.1/etc/hadoop/core-site.xml"));
        //conf.addResource(new Path("/Users/edwardzhu/hadoop-2.10.1/etc/hadoop/hdfs-site.xml"));

        if(args.length != 2) {
            System.out.println("Error, need  2 command line arguments");
            System.exit(0);
        }
        Path path_in = new Path(args[0]);
        Path path_out = new Path(args[1]);
        FileSystem fs_input = path_in.getFileSystem(conf);
        FileSystem fs_output = path_out.getFileSystem(conf);

        if(!fs_input.exists(path_in)){
            System.out.println("File " + args[0] + " not found!");
            System.exit(0);
        }

        if(fs_output.exists(path_out)){
            System.out.println("File " + args[1] + " already exists!");
            System.exit(0);
        }

        FSDataInputStream in_file = fs_input.open(path_in);
        FSDataOutputStream out_file = fs_output.create(path_out);
        long timeStart = System.nanoTime();
        byte[] b = new byte[4096];
        int numBytes = 0;
        long byteCount = 0;
        while((numBytes = in_file.read(b)) > 0){
            out_file.write(b, 0, numBytes);
            byteCount += numBytes;
        }

        in_file.close();
        out_file.close();
        long endTime = System.nanoTime();
        double totalTime = (endTime - timeStart) / 10e8;
        System.out.println("Copied " + byteCount + " bytes from " + "'" + args[0] + "'"+ " to " + "'" + args[1] + "'" + " in " + totalTime + " seconds");
    }
}
