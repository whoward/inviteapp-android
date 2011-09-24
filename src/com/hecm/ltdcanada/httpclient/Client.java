package com.hecm.ltdcanada.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Base64;

import com.hecm.ltdcanada.httpclient.model.Model;
import com.hecm.ltdcanada.httpclient.models.Account;
import com.hecm.ltdcanada.httpclient.models.Invitation;
import com.hecm.ltdcanada.httpclient.models.Speaker;
import com.hecm.util.Util;

public class Client {
	@SuppressWarnings("unused")
	private static final String TAG = "Client";
	
	private String host;
	private String username;
	private String password;
	private int port;
	
	private static final String INVITATIONS_RESOURCE = "pqv_invitations";
	private static final String ACCOUNTS_RESOURCE = "accounts";
	private static final String SPEAKERS_RESOURCE = "speakers";
	
	public Client(String host, int port, String username, String password) {
		this.setHost(host);
		this.setUsername(username);
		this.setPassword(password);
		this.setPort(port);
	}
	
	// -----------------------------------------------
	// Invitation Methods
	// -----------------------------------------------
	
	public List<Invitation> getAllInvitations() throws ClientException, IOException  {
		return this.<Invitation>getResourceCollection(Invitation.class, INVITATIONS_RESOURCE);
	}
	
	public Invitation getInvitation(String id) throws ClientException, IOException {
		return this.<Invitation>getResource(Invitation.class, INVITATIONS_RESOURCE, id);
	}
	
	public void createInvitation(Invitation i) throws ClientException, IOException {
		this.<Invitation>createResource(Invitation.class, INVITATIONS_RESOURCE, i);
	}
	
	public void updateInvitation(Invitation i) throws ClientException, IOException {
		this.<Invitation>updateResource(Invitation.class, INVITATIONS_RESOURCE, i);
	}
	
	public void deleteInvitation(Invitation i) throws ClientException, IOException {
		this.<Invitation>deleteResource(Invitation.class, INVITATIONS_RESOURCE, i);
	}

	// -----------------------------------------------
	// Account Methods
	// -----------------------------------------------
	
	public List<Account> getAllAccounts() throws ClientException, IOException {
		return this.<Account>getResourceCollection(Account.class, ACCOUNTS_RESOURCE);
	}
	
	public Account getAccount(String id) throws ClientException, IOException {
		return this.<Account>getResource(Account.class, ACCOUNTS_RESOURCE, id);
	}
	
	public void createAccount(Account a) throws ClientException, IOException {
		this.<Account>createResource(Account.class, ACCOUNTS_RESOURCE, a);
	}
	
	public void updateAccount(Account a) throws ClientException, IOException {
		this.<Account>updateResource(Account.class, ACCOUNTS_RESOURCE, a);
	}
	
	public void deleteAccount(Account a) throws ClientException, IOException {
		this.<Account>deleteResource(Account.class, ACCOUNTS_RESOURCE, a);
	}
	
	public Account getUserAccount() throws ClientException, IOException {
		return this.<Account>getResource(Account.class, ACCOUNTS_RESOURCE, "profile");
	}

	// -----------------------------------------------
	// Speaker Methods
	// -----------------------------------------------	
	
	public List<Speaker> getAllSpeakers() throws ClientException, IOException {
		return this.<Speaker>getResourceCollection(Speaker.class, SPEAKERS_RESOURCE);
	}
	
	public Speaker getSpeaker(String id) throws ClientException, IOException {
		return this.<Speaker>getResource(Speaker.class, SPEAKERS_RESOURCE, id);
	}
	
	public void createSpeaker(Speaker speaker) throws ClientException, IOException {
		this.<Speaker>createResource(Speaker.class, SPEAKERS_RESOURCE, speaker);
	}
	
	public void updateSpeaker(Speaker speaker) throws ClientException, IOException {
		this.<Speaker>updateResource(Speaker.class, SPEAKERS_RESOURCE, speaker);
	}
	
	public void deleteSpeaker(Speaker speaker) throws ClientException, IOException {
		this.<Speaker>deleteResource(Speaker.class, SPEAKERS_RESOURCE, speaker);
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return this.port;
	}
	
	private <T extends Model> List<T> getResourceCollection(Class<T> klass, String resource) throws ClientException, IOException {
		String url = this.buildUrl(String.format("/%s", resource));
				
		HttpGet request = new HttpGet(url);
		
		this.assignJsonHeaders(request);
		
		JSONArray body;
		try {
			body = new JSONArray(this.executeRequest(request));
		} catch (JSONException e) {
			throw new ServerException(e);
		}
		
		List<T> models = new ArrayList<T>();
		try {		
			for(int i = 0; i < body.length(); i++) {
				models.add(this.instantiateModel(klass, body.getJSONObject(i)));
			}
		} catch (JSONException e) {
			throw new ServerException(e);
		}
		
		return models;
	}

	private <T extends Model> T getResource(Class<T> klass, String resource, String id) throws ClientException, IOException {
		String url = this.buildUrl(String.format("/%s/%s", resource, id));
		
		HttpGet request = new HttpGet(url);
		
		this.assignJsonHeaders(request);
		
		try {
			return this.instantiateModel(klass, new JSONObject(this.executeRequest(request)));
		} catch (JSONException e) {
			throw new ServerException(e);
		}
	}
	
	private <T extends Model> void createResource(Class<T> klass, String resource, Model model) throws ClientException, IOException {
		String url = this.buildUrl(String.format("/%s", resource));
		
		HttpPost request = new HttpPost(url);
		
		this.assignFormHeaders(request, model.getEncodedValues());
		
		try {
			this.executeRequest(request);
		} catch (JSONException e) {
			throw new ServerException(e);
		}
	}
	
	private <T extends Model> void updateResource(Class<T> klass, String resource, Model model) throws ClientException, IOException {
		Long modelId = model.getInteger("ID");
		
		if(modelId == null) {
			throw new IllegalArgumentException("id must be defined on the model to update it");
		}
		
		String url = this.buildUrl(String.format("/%s/%s", resource, modelId.toString()));
		
		HttpPut request = new HttpPut(url);
		
		this.assignFormHeaders(request, model.getEncodedValues());
		
		try {
			this.executeRequest(request);
		} catch (JSONException e) {
			throw new ServerException(e);
		}
	}
	
	private <T extends Model> void deleteResource(Class<T> klass, String resource, Model model) throws ClientException, IOException {
		Long modelId = model.getInteger("ID");
		
		if(modelId == null) {
			throw new IllegalArgumentException("id must be defined on the model to update it");
		}
		
		String url = this.buildUrl(String.format("/%s/%s", resource, modelId.toString()));
		
		HttpDelete request = new HttpDelete(url);
		
		
		this.assignJsonHeaders(request);
		
		try {
			this.executeRequest(request);
		} catch (JSONException e) {
			throw new ServerException(e);
		}
	}
	
	private <T extends Model> T instantiateModel(Class<T> klass, JSONObject json) throws JSONException {
		// Metaprogrammically instantiate the model (requires a default no-parameter constructor to be 
		// defined).  If an error occurs instantiating it, re-raise it as a runtime exception (unchecked exception)
		T instance;
		
		try {
			instance = klass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
		// assign the values for it from the JSON object
		instance.setValues(json);
		
		// return the new instance
		return instance;
	}
	
	private String buildUrl(String path) {
		return String.format("http://%s:%d%s", this.getHost(), this.getPort(), path);
	}
	
	private HttpClient buildClient() {
		return new DefaultHttpClient();
	}
	
	private String getAuthorizationHeader() {
		String auth = this.username + ":" + this.password;
		return "Basic " + Base64.encodeToString(auth.getBytes(), Base64.URL_SAFE|Base64.NO_WRAP);
	}
	
	private void assignJsonHeaders(HttpRequest request) {
		// Set the appropriate headers for JSON communication
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");
		request.setHeader("Authorization", this.getAuthorizationHeader());
	}
	
	private void assignFormHeaders(HttpEntityEnclosingRequestBase request, Map<String, String> parameters) throws UnsupportedEncodingException {
		// Set the appropriate headers for JSON communication
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/x-www-form-urlencoded");
		request.setHeader("Authorization", this.getAuthorizationHeader());
		
		// convert the map to url encoded form variables and set that as the entity of the request
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		for(Map.Entry<String, String> pair : parameters.entrySet()) {
			String key = pair.getKey();
			String value = pair.getValue();
			
			// if the value is null or contains only whitespace then do not include it
			if(value == null || value.matches("^\\s+$")) {
				continue;
			}
			
			// otherwise add the value to the pair list
			nvps.add(new BasicNameValuePair(key, value));
		}
		
		request.setEntity(new UrlEncodedFormEntity(nvps));
	}
	
	private String executeRequest(HttpUriRequest request) throws InvalidCredentialsException, UnauthorizedException, ServerException, IOException, NotFoundException, ValidationException, JSONException {
		HttpClient client = this.buildClient();
		HttpResponse response = client.execute(request);
		
		int code = response.getStatusLine().getStatusCode();
		
		String body = Util.streamToString(response.getEntity().getContent());
		
		switch(code) {
			case 200: // HTTP OK
				// do nothing special
				break;
			case 401: // HTTP UNAUTHORIZED
				throw new InvalidCredentialsException();
			
			case 400: // HTTP BAD REQUEST
				throw new ValidationException(new JSONObject(body));
				
			case 403: // HTTP FORBIDDEN
				throw new UnauthorizedException();
			
			case 404: // HTTP NOT FOUND
				throw new NotFoundException();
				
			case 500: // HTTP INTERNAL SERVER ERROR
				throw new ServerException();
				
			default:
				throw new ServerException("unhandled status code: " + Integer.toString(code));
		}
		
		return body;
	}
}
