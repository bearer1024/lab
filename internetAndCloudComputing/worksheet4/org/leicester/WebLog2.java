// Worksheet 4, Part 2
package org.leicester;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WebLog2 {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, Text>{

    private Text word = new Text();
    private Text word2 = new Text();

    // mapper outputs username|url as key and date as value for every url visit by username
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());

      String username=itr.nextToken();
      String date=itr.nextToken();
      String url=itr.nextToken();

      word.set(username+"|"+url);
      word2.set(date);

      context.write(word, word2);
    }
  }

    // reducer checks if some date occurs at least twice and, if so, emits username as key and url as value
  public static class DateReducer
       extends Reducer<Text,Text,Text,Text> {
    private Text word = new Text();
    private Text word2 = new Text();

    public void reduce(Text key, Iterable<Text> values,
                       Context context
                       ) throws IOException, InterruptedException {
      HashSet<String> S = new HashSet<String>();

      boolean found=false;

      for (Text val : values) {
        String d = val.toString();
        if (S.contains(d)) {
	  found = true;
	  break;
	}
	else {
	  S.add(d);
	}
      }
      if (found) {
	  String K = key.toString();
	  String[] A = K.split("\\|");
	  word.set(A[0]);
	  word2.set(A[1]);
	  context.write(word, word2);
      }
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Web Log 2");
    job.setJarByClass(WebLog2.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setReducerClass(DateReducer.class);
    job.setOutputKeyClass(Text.class); // output key type for mapper
    job.setOutputValueClass(Text.class); // output value type for mapper
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
