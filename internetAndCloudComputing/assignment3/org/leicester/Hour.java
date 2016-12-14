
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
    private Text inputs = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
	    StringTokenizer itr = new StringTokenizer(value.toString(),",");
	    String accident_Index = itr.nextToken();
	    String location_Easting_OSGR = itr.nextToken();
	    String location_Northing_OSGR = itr.nextToken();
	    String longitude = itr.nextToken();
            String latitude = itr.nextToken();
	    String police_Force = itr.nextToken();
	    String accident_Severity = itr.nextToken();
	    String number_of_Vehicles = itr.nextToken();
	    String number_of_Casualties = itr.nextToken();
	    String date = itr.nextToken();
	    String day_of_Week = itr.nextToken();
	    String time = itr.nextToken();
	    String timeForHour = time.split(":")[0];
	    String finalDay = " ";
	    try{
	    int finalDayInt = Integer.parseInt(day_of_Week);
	    if((finalDayInt) >= 2 && (finalDayInt) <= 6){
		   finalDay = "WD";
	    }else if(finalDayInt==1 || finalDayInt == 7){
		    finalDay = "WE";
	    }
	    String local_Authority_District = itr.nextToken();
	    String local_Authority_HighwayAndOthers = itr.nextToken();

	    inputs.set(finalDay+"|"+timeForHour);

	    context.write(inputs,one);

	    }catch (NumberFormatException e){
	    System.out.println("here has numberFormatException, but it doesn't matter");
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
