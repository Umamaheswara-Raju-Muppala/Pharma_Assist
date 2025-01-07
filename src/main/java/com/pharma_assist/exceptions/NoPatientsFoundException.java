package com.pharma_assist.exceptions;
@SuppressWarnings("serial")
public class NoPatientsFoundException extends RuntimeException {
	private String message;

	public NoPatientsFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
