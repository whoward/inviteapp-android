package com.hecm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public final class Util {
	public static String join(List<String> strings) {
		return join(strings, ", ");
	}
	
	public static String join(List<String> strings, String separator) {
	    StringBuffer sb = new StringBuffer();
	    for (int i=0; i < strings.size(); i++) {
	        if (i != 0) sb.append(separator);
	  	    sb.append(strings.get(i));
	  	}
	  	return sb.toString();
	}
	
	public static String streamToString(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		
		String line = "";
		while((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		
		return sb.toString();
	}
}
