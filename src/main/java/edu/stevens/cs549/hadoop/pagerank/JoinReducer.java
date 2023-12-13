package edu.stevens.cs549.hadoop.pagerank;

import java.io.*;
import java.util.Iterator;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

	public class JoinReducer extends Reducer<TextPair, Text, Text, Text> {

		public void reduce(TextPair key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			/*
			 * TextPair ensures that we have values with tag "0" first, followed by tag "1"
			 * So we know that first value is the name and second value is the rank
			 */
			String vertexId = key.getFirst().toString();
			String k = key.toString(); // Converts the key to a String

			// values should have the vertex name and the page rank (in that order).
			// Emit (vertex name, pagerank) or (vertex id, vertex name, pagerank)
			// Ignore if the values do not include both vertex name and page rank
			Iterator<Text> iterator = values.iterator();
			String vertexName = null;
			Double pageRank = null;

			while (iterator.hasNext()) {
				String valueStr = iterator.next().toString();
				vertexName = valueStr;
				if(iterator.hasNext()) {
					pageRank = Double.parseDouble(iterator.next().toString());
				}
			}

			if (vertexName != null && pageRank != null) {
				context.write(new Text(vertexId), new Text(vertexName + "\t" + String.valueOf(pageRank)));
			}
		}
	}


