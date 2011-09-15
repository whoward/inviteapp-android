package com.hecm.ltdcanada.httpclient;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hecm.util.Util;

public class ValidationException extends ClientException {
	private static final long serialVersionUID = 1L;
	
	private List<String> errors;
	
	public ValidationException() {
		this.errors = new ArrayList<String>();
	}
	
	public ValidationException(JSONObject json) throws JSONException {
		this();
		
		JSONArray errors = json.getJSONArray("errors");
		
		for(int i = 0; i < errors.length(); i++) {
			this.errors.add(errors.getString(i));
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("ValidationException:\n\t");
		sb.append(Util.join(this.errors, "\n\t"));
		
		return sb.toString();
	}
}
