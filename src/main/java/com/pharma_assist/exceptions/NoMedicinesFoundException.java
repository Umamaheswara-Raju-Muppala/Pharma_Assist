package com.pharma_assist.exceptions;

@SuppressWarnings("serial")
public class NoMedicinesFoundException extends RuntimeException {
	private String message;

	public NoMedicinesFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
