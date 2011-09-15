package com.hecm.ltdcanada.httpclient;

public abstract class ClientException extends Exception {
	public ClientException() {
		
	}
	
	public ClientException(Exception e) {
		super(e);
	}

	public ClientException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
