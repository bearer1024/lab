/*
 *modified based on Hadoop WordCount.java
 *uses day string and the hour of time as key, 1 as value for maspper output
 *cout the number of accidents in reducer side
 */
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

public class Hour{

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text text = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
	    //use split method to get a string array
	    String[] inputs = value.toString().split(",");
	    String day_of_Week = inputs[10];
	    String time = inputs[11];
	    //ignore blank filed and the name of it's field 
	    if(!day_of_Week.equals(null) && day_of_Week.length() != 0 && !day_of_Week.equals("Day_of_Week")){

		    if(!time.equals(null) && time.length() != 0 && !time.equals("Time")){
		    //get first part of time used to stand for each hour
		    String timeForHour = time.split(":")[0];
		    String finalDay = " ";
		    int finalDayInt = Integer.parseInt(day_of_Week);
		    //workday
		    if((finalDayInt) >= 2 && (finalDayInt) <= 6){
			   finalDay = "WD";
		    }else if(finalDayInt==1 || finalDayInt == 7){//weekend
			    finalDay = "WE";
		    }

		    if(!timeForHour.equals(null)){
		    //use day string : time for hour as key of mapper's output
		    text.set(finalDay+":"+timeForHour);
		    }

		    context.write(text,one);
		    }
		    }
		    }
  }

  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      //count the number of accidents
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Hour");
    job.setJarByClass(Hour.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
