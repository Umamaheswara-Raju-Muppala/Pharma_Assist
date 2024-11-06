package com.pharma_assist.exceptions;
@SuppressWarnings("serial")
public class InvalidFormException extends RuntimeException {

	private String message;

	public InvalidFormException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
