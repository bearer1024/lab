package org.leicester;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class District {

  public static class TokenizerMapper
       extends Mapper<Object, Text, IntWritable, Text>{

    private IntWritable district = new IntWritable();
    private Text dateWritable = new Text();
    private final static IntWritable one = new IntWritable(1);

    // mapper outputs username|url as key and date as value for every url visit by username
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
	    String[] inputs = value.toString().split(",");
	    String dateFromCsv = inputs[9];
	    String districtFromCsv = inputs[12];
	    if(!dateFromCsv.equals(null) && dateFromCsv.length() != 0 && !dateFromCsv.equals("Date")){
		    if(!districtFromCsv.equals(null) && districtFromCsv.length() != 0 && !districtFromCsv.equals("Local_Authority_(District)")){
		    dateWritable.set(dateFromCsv);
		    district.set(Integer.parseInt(districtFromCsv));
		    context.write(district,dateWritable);
		    }
		    }
		    }
		    }

    // reducer checks if some date occurs at least twice and, if so, emits username as key and url as value
  public static class DateReducer
       extends Reducer<IntWritable,Text,IntWritable,Text> {
    private Text days = new Text();
    private IntWritable district = new IntWritable();

    public void reduce(IntWritable key, Iterable<Text> values,
                       Context context ) throws IOException, InterruptedException {
      HashMap<String,Integer> hashMap = new HashMap<String,Integer>();

      boolean found=false;

      for (Text value : values) {
        String date = value.toString();
	int dateOccurTime = 1;
        if (hashMap.containsKey(date)) {
	  dateOccurTime += hashMap.get(date);
	}
	hashMap.put(date,dateOccurTime);
      }
      int sum = 0;
      for(Map.Entry<String,Integer> entry : hashMap.entrySet()){
	      if(entry.getValue() >= 5) sum++;
      }
      days.set(Integer.toString(sum));
      context.write(key,days);
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "District");
    job.setJarByClass(District.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setReducerClass(DateReducer.class);
    job.setOutputKeyClass(IntWritable.class); // output key type for mapper
    job.setOutputValueClass(Text.class); // output value type for mapper
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
