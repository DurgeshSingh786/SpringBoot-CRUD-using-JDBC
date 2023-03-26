package com.example.tensent;

public class BadPersonRequestException extends Exception {
private static final long serialVersionUID = 1L;
	
//	String applicationStatusCode;
	public BadPersonRequestException(String message) {
		super(message);
	}
}
