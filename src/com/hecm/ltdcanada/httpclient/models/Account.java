package com.hecm.ltdcanada.httpclient.models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.hecm.ltdcanada.httpclient.model.Definition;
import com.hecm.ltdcanada.httpclient.model.Model;

public class Account extends Model {

	private static final long serialVersionUID = 1L;
	
	public static List<Definition> attributes = new ArrayList<Definition>();
	
	static {
		attributes.add(new Definition("ID",             Definition.Type.INTEGER));
		attributes.add(new Definition("Email",          Definition.Type.STRING));
		attributes.add(new Definition("FirstName",      Definition.Type.STRING));
		attributes.add(new Definition("LastName",       Definition.Type.STRING));
		attributes.add(new Definition("Role",           Definition.Type.STRING));
		attributes.add(new Definition("LastSignedInAt", Definition.Type.DATE));
	}
	
	public Account() {
		super(attributes);
	}
	
	public Account(JSONObject json) throws JSONException {
		super(attributes);
		this.setValues(json);
	}
	
	public String getIdentifyingName() {
		String firstName = this.getString("FirstName");
		String lastName = this.getString("LastName");
		
		if(firstName == null && lastName == null) {
			return this.getString("Email");
		} else {
			if(firstName == null) {
				firstName = "";
			}
			if(lastName == null) {
				lastName = "";
			}
			return (firstName + " " + lastName).trim(); 
		}
	}
	
}
