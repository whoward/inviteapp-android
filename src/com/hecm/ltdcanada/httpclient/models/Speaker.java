package com.hecm.ltdcanada.httpclient.models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.hecm.ltdcanada.httpclient.model.Definition;
import com.hecm.ltdcanada.httpclient.model.Model;

public class Speaker extends Model {
	public static List<Definition> attributes = new ArrayList<Definition>();
	
	static {
		attributes.add(new Definition("ID",        Definition.Type.INTEGER));
		attributes.add(new Definition("Name",      Definition.Type.STRING));
		attributes.add(new Definition("BaseURL",   Definition.Type.STRING));
	}
	
	public Speaker() {
		super(attributes);
	}
	
	public Speaker(JSONObject json) throws JSONException {
		super(attributes);
		this.setValues(json);
	}
}
