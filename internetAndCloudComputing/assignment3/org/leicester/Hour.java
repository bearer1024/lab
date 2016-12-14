
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
	    String[] inputs = value.toString().split(",");
	    String day_of_Week = inputs[10];
	    String time = inputs[11];
	    if(!day_of_Week.equals(null) && day_of_Week.length() != 0 && !day_of_Week.equals("Day_of_Week")){

		    if(!time.equals(null) && time.length() != 0 && !time.equals("Time")){
		    String timeForHour = time.split(":")[0];
		    String finalDay = " ";
		    int finalDayInt = Integer.parseInt(day_of_Week);
		    if((finalDayInt) >= 2 && (finalDayInt) <= 6){
			   finalDay = "WD";
		    }else if(finalDayInt==1 || finalDayInt == 7){
			    finalDay = "WE";
		    }

		    if(!timeForHour.equals(null)){

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
