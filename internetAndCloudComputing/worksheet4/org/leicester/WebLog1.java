// Worksheet 4, Part 1
package org.leicester;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WebLog1 {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    // mapper outputs username|url as key and 1 as value for every url visited by username
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());

      String username=itr.nextToken();
      String date=itr.nextToken();
      String url=itr.nextToken();

      word.set(username+"|"+url);

      context.write(word, one);
    }
  }

    // reducer counts occurrences of username|url and outputs username as key and url as value if the count is at least 2
  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,Text> {
    private Text word = new Text();
    private Text word2 = new Text();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      if (sum>=2) {
	String S = key.toString();
	String[] A = S.split("\\|");
	word.set(A[0]);
	word2.set(A[1]);
	context.write(word, word2);
      }
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Web Log 1");
    job.setJarByClass(WebLog1.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class); // output key type for mapper
    job.setOutputValueClass(IntWritable.class); // output value type for mapper
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
