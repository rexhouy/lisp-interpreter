package com.rexhouy.reader;

import java.util.ArrayList;
import java.util.List;

public class ListParser {
	
	/**
	 * Parse every elements into list.
	 * 
	 * @param input
	 * @return
	 */
	public static List<String> parse(String input) {
		List<String> ret = new ArrayList<String>();
		StringBuilder sb = new StringBuilder(input);
		int lastPos = 0;
		boolean inString = false;
		for (int i = 0; i < input.length(); i++) {
			String currentChar = sb.substring(i, i+1);
			
			//String start
			if (!inString && currentChar.equals("\"")) {
				if (lastPos != i) {
					ret.add(sb.substring(lastPos, i));
				}
				inString = true;
				continue;
			}
			
			// String end
			if (inString) {
				if (currentChar.equals("\"")) {
					ret.add(sb.substring(lastPos, i+1));
					lastPos = i+1;
					inString = false;
				}
				continue;
			}
			
			if (currentChar.equals("(") || currentChar.equals(")")) {
				if (lastPos != i) {
					ret.add(sb.substring(lastPos, i));
				}
				ret.add(currentChar);
				lastPos = i+1;
				continue;
			}
			
			if (currentChar.equals(" ")) {
				if (lastPos != i) {
					ret.add(sb.substring(lastPos, i));
				}
				lastPos = i+1;
			}
		}
		if (lastPos < input.length()-1) {
			ret.add(sb.substring(lastPos));
		}
		return ret;
	}
	
}
