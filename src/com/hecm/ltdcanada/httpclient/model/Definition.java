package com.hecm.ltdcanada.httpclient.model;

public class Definition {
	public enum Type { STRING, INTEGER, REAL, DATE };
	
	private String name;
	private Type type;
	
	public Definition(String name, Type type) {
		this.setName(name);
		this.setType(type);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
}
