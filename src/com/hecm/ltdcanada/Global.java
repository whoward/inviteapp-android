package com.hecm.ltdcanada;

import java.util.Map;

import android.util.Log;

import com.hecm.ltdcanada.httpclient.Client;
import com.hecm.util.Util;

public final class Global {
	private static Client client;
	private static String login;
	private static String password;
	private static String apiHost;
	private static Integer apiPort;
	
	private static String[] knownPreferenceKeys = {};
	
	public static Client getClient() {
		if(client == null) {
			client = new Client(apiHost, apiPort, login, password);
		}
		return client;
	}
	
	public static void preferenceUpdated(String key, String value) {
		Log.i("PreferenceUpdated", key + " - " + value);
		if(key.equals("login")) {
			login = value;
			if(client != null) {
				client.setUsername(login);
			}
		}
		
		if(key.equals("password")) {
			password = value;
			if(client != null) {
				client.setPassword(password);
			}
		}
		
		if(key.equals("apiHost")) {
			apiHost = value;
			client = null;
		}
		
		if(key.equals("apiPort")) {
			apiPort = Integer.parseInt(value);
			client = null;
		}
	}
	
	public static void setPreferences(Map<String, ?> prefs) {
		for(Map.Entry<String, ?> e : prefs.entrySet()) {
			String key = e.getKey();
			Object value = e.getValue();
			
			preferenceUpdated(key, (String) value);
		}
	}
}
