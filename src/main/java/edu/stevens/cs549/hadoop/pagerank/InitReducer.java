package edu.stevens.cs549.hadoop.pagerank;

import java.io.*;
import java.util.Iterator;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class InitReducer extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		/*
		 *  Output key: node+rank, value: adjacency list
		 */
		Double initialPageRank = 1.0;
		Iterator<Text> text = values.iterator();
		while (text.hasNext()) {
			context.write(new Text(key + "+" + initialPageRank), text.next());

		}
	}
}

