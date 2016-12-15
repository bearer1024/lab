/*
 *Mapper side output district as key, date of each accident as value to the reducer
 *Reducer side uses HashMap to store the number of accidents happen for each day,
 *then it filters and counts only those days which have over 5 accidents.
 * The Reducer's output uses district as key and numberOfDays as value;
 */
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

    //using IntWritable type because it's convinent to do sorting
    private IntWritable district = new IntWritable();
    private Text dateWritable = new Text();

    // mapper outputs district as key and date as value for every accident
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
	    String[] inputs = value.toString().split(",");
	    String dateFromCsv = inputs[9];
	    String districtFromCsv = inputs[12];
	    //ignore blank field in csv files
	    if(!dateFromCsv.equals(null) && dateFromCsv.length() != 0 && !dateFromCsv.equals("Date")){
		    if(!districtFromCsv.equals(null) && districtFromCsv.length() != 0 && !districtFromCsv.equals("Local_Authority_(District)")){
		    dateWritable.set(dateFromCsv);
		    district.set(Integer.parseInt(districtFromCsv));
		    context.write(district,dateWritable);
		    }
	    }
	    }
    }

    // reducer checks if some date occurs at least 5 times, if so, emits district as key and numberOfDays which has over 5 accidents  as value
  public static class DateReducer
       extends Reducer<IntWritable,Text,IntWritable,Text> {
    private Text numberOfDays = new Text();
    private IntWritable district = new IntWritable();

    public void reduce(IntWritable key, Iterable<Text> values,
                       Context context ) throws IOException, InterruptedException {
      //use hashMap structure to store date as key, the occur time of each date as value
      HashMap<String,Integer> hashMap = new HashMap<String,Integer>();

      for (Text value : values) {
        String date = value.toString();
	int dateOccurTime = 1;
	//cout dateOccurTime
        if (hashMap.containsKey(date)) {
	  dateOccurTime += hashMap.get(date);
	}
	//add to hashMap
	hashMap.put(date,dateOccurTime);
      }
      int sum = 0;
      for(Map.Entry<String,Integer> entry : hashMap.entrySet()){
	   //if that day has over 5 accident then it could be counted into numberOfDays
	      if(entry.getValue() >= 5) sum++;
      }
      numberOfDays.set(Integer.toString(sum));
      context.write(key,numberOfDays);
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
