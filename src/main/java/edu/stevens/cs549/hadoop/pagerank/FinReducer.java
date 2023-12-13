package edu.stevens.cs549.hadoop.pagerank;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FinReducer extends Reducer<DoubleWritable, Text, Text, Text> {
	
//	@Override
//	public void setup(Context context) {
//		try {
//			super.setup(context);
//			URI[] files = context.getCacheFiles();
//		} catch (IOException | InterruptedException e) {
//			//  Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void reduce(DoubleWritable key, Iterable<Text> values, Context context) throws IOException,
			InterruptedException {
		/* 
		 *  For each value, emit: key:value, value:-rank
		 */
		Iterator<Text> iterator = values.iterator();
		String node;
		while(iterator.hasNext()) {
			node = iterator.next().toString();
			context.write(new Text(node.split(" ")[0] ) , new Text((String.valueOf(0 - key.get()))+ " " +node.split(" ")[1]));
//			context.write(new Text(node), new Text(String.valueOf(0 - key.get())));
		}
	}
}
