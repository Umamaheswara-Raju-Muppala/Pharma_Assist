package com.pharma_assist.exceptions;

public class BillNotFoundException extends RuntimeException {
	public BillNotFoundException(String message) {
		super(message);
	}
}
