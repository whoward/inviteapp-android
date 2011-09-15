package com.hecm.ltdcanada.httpclient;

public class ServerException extends ClientException {
	public ServerException() {}
	
	public ServerException(Exception e) {
		super(e);
	}

	public ServerException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
