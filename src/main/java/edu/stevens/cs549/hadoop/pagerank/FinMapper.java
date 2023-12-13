package edu.stevens.cs549.hadoop.pagerank;

import java.io.IOException;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

public class FinMapper extends Mapper<LongWritable, Text, DoubleWritable, Text> {

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException, IllegalArgumentException {
		 // Converts Line to a String
		/*
		 * output key:-rank, value: node
		 * See IterMapper for hints on parsing the output of IterReducer.
		 */
		String line = value.toString();
		String[] parts = line.split("\t");
		long nodeId = Long.parseLong(parts[0]);
		double pageRank = Double.parseDouble(parts[2]);
		String vertex = parts[1];

		if(parts.length>3) {
			throw new IllegalArgumentException("Bad Input");

		}
		if(parts.length!=3) {
			return;
		}

		context.write(new DoubleWritable(0-pageRank), new Text(nodeId + " " + vertex));



	}

}
