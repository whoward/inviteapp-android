package com.hecm.ltdcanada.httpclient.models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;

import com.hecm.ltdcanada.httpclient.model.Definition;
import com.hecm.ltdcanada.httpclient.model.Model;

public final class Invitation extends Model {

	public static List<Definition> attributes = new ArrayList<Definition>();
	
	static {
		attributes.add(new Definition("ID",        Definition.Type.INTEGER));
		attributes.add(new Definition("SpeakerID", Definition.Type.INTEGER));
		attributes.add(new Definition("Name",      Definition.Type.STRING));
		attributes.add(new Definition("Email",     Definition.Type.STRING));
		attributes.add(new Definition("Token",     Definition.Type.STRING));
		attributes.add(new Definition("Views",     Definition.Type.INTEGER));
		attributes.add(new Definition("MaxViews",  Definition.Type.INTEGER));
		attributes.add(new Definition("Status",    Definition.Type.STRING));
		attributes.add(new Definition("SentAt",    Definition.Type.DATE));
		attributes.add(new Definition("ExpiresAt", Definition.Type.DATE));
		attributes.add(new Definition("ViewedAt",  Definition.Type.DATE));
	}
	
	public Invitation() {
		super(attributes);
	}
	
	public Invitation(JSONObject json) throws JSONException {
		super(attributes);
		this.setValues(json);
	}
	
	public boolean isExpired() {
		return this.getString("Status").equals("Expired");
	}

	public CharSequence getViewsString() {
		return String.format("%d/%d", this.getInteger("Views"), this.getInteger("MaxViews"));
	}

	public int getStatusColor() {
		return Color.parseColor(isExpired() ? "#bb0000" : "#00bb00");
	}
	
}
