package edu.stevens.cs549.hadoop.pagerank;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class JoinRankMapper extends Mapper<LongWritable, Text, TextPair, Text> {

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString(); // Converts Line to a String
		String[] sections = line.split("\\s+"); // Splits it into two parts. Part 1: node | Part 2: rank

		if (sections.length >= 2) {
			String[] rankAndnode = sections[0].split("\\+");
			String node = rankAndnode[0];
			String rank = rankAndnode[1];
			context.write(new TextPair(node, "1"), new Text(rank));
		}

	}
}
