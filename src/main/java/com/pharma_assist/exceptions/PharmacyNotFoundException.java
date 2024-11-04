package com.pharma_assist.exceptions;

@SuppressWarnings("serial")
public class PharmacyNotFoundException extends RuntimeException {
	private String message;

	public PharmacyNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
