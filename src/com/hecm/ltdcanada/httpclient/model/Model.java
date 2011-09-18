package com.hecm.ltdcanada.httpclient.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Model implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Definition> definitions;
	private Map<Definition, Object> values;

	public Model() {
		this.definitions = new ArrayList<Definition>();
		this.values = new HashMap<Definition, Object>();
	}

	public Model(List<Definition> definitions) {
		this();
		this.setDefinitions(definitions);
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<Definition> definitions) {
		this.definitions = definitions;
	}

	public void setValues(Map<String, Object> values) {
		for (Map.Entry<String, Object> entry : values.entrySet()) {
			try {
				this.set(entry.getKey(), entry.getValue());
			} catch (UndefinedAttributeException e) {
				// do nothing
			}

		}
	}

	public void setValues(JSONObject json) throws JSONException {
		JSONArray names = json.names();
		for (int i = 0; i < names.length(); i++) {
			// determine the name and definition
			String name = names.getString(i);
			Definition def = this.getDefinitionForName(name);

			// ignore null values or undefined attributes
			if (json.isNull(name) || def == null) {
				continue;
			}

			// depending on the type of the attribute assign a value parsed from
			// JSON
			switch (def.getType()) {
			case INTEGER:
				this.set(def, json.getLong(name));
				break;

			case REAL:
				this.set(def, json.getDouble(name));
				break;

			case STRING:
				this.set(def, json.getString(name));
				break;

			case DATE:
				this.set(def, new Date(json.getLong(name)));
				break;
			}
		}
	}

	public Map<String, String> getEncodedValues() {
		Map<String, String> values = new HashMap<String, String>();

		for (Definition def : this.definitions) {
			String name = def.getName();
			values.put(name, this.encodeValue(name, def));
		}

		return values;
	}

	public Object get(String definitionName) {
		Definition def = this.getDefinitionForName(definitionName);

		if (def == null) {
			throw new UndefinedAttributeException(String.format(
					"the attribute named %s is not defined", definitionName));
		}

		return this.values.get(def);
	}

	public Long getInteger(String definitionName) {
		Object value = this.get(definitionName);
		if (value == null) {
			return null;
		} else {
			return (Long) value;
		}
	}

	public String getString(String definitionName) {
		Object value = this.get(definitionName);
		if (value == null) {
			return null;
		} else {
			return (String) value;
		}
	}

	public Double getReal(String definitionName) {
		Object value = this.get(definitionName);
		if (value == null) {
			return null;
		} else {
			return (Double) value;
		}
	}

	public Date getDate(String definitionName) {
		Object value = this.get(definitionName);
		if (value == null) {
			return null;
		} else {
			return (Date) value;
		}
	}

	public void set(String definitionName, Object value) {
		Definition def = this.getDefinitionForName(definitionName);

		if (def == null) {
			throw new UndefinedAttributeException(String.format(
					"the attribute named %s is not defined", definitionName));
		}

		this.set(def, value);
	}

	public void set(Definition def, Object value) {
		switch (def.getType()) {
			case INTEGER:
				if (value instanceof Integer) {
					value = new Long((Integer) value);
				}
				break;
				
			case REAL:
				if (value instanceof Float) {
					value = new Double((Float) value);
				}
				break;
				
			case STRING:
				break;
				
			case DATE:
				if (value instanceof Long) {
					value = new Date((Long) value);
				}
				break;
			}
		
		this.values.put(def, value);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.getClass().getName() + ":\n");

		for (Definition def : this.definitions) {
			sb.append('\t');
			sb.append(def.getName());
			sb.append(": ");
			sb.append(this.inspectValue(def.getName(), def));
			sb.append('\n');
		}

		return sb.toString();
	}

	protected String encodeValue(String name) {
		Definition def = this.getDefinitionForName(name);

		if (def == null) {
			throw new UndefinedAttributeException(String.format(
					"the attribute named %s is not defined", name));
		}

		return this.encodeValue(name, def);
	}

	protected String encodeValue(String name, Definition definition) {
		Object value = this.get(name);

		if (value == null)
			return null;

		switch (definition.getType()) {
		case INTEGER:
			return Long.toString((Long) value);

		case REAL:
			return Double.toString((Double) value);

		case STRING:
			return ((String) value).toString();

		case DATE:
			return Long.toString(((Date) value).getTime());

		default:
			throw new RuntimeException("WTF");
		}
	}

	protected String inspectValue(String name) {
		Definition def = this.getDefinitionForName(name);

		if (def == null) {
			throw new UndefinedAttributeException(String.format(
					"the attribute named %s is not defined", name));
		}

		return this.inspectValue(name, def);
	}

	protected String inspectValue(String name, Definition def) {
		Object value = this.values.get(def);
		switch (def.getType()) {
		case INTEGER:
			return value == null ? "null" : Long.toString((Long) value);
		case REAL:
			return value == null ? "null" : Double.toString((Double) value);
		case STRING:
			return value == null ? "null" : (String) value;
		case DATE:
			return value == null ? "null" : ((Date) value).toString();
		default:
			throw new RuntimeException("WTF");
		}
	}

	protected Definition getDefinitionForName(String name) {
		for (Definition d : this.definitions) {
			if (d.getName().equals(name)) {
				return d;
			}
		}
		return null;
	}

}
