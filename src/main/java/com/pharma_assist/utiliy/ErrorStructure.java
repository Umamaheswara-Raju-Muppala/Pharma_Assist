package com.pharma_assist.utiliy;

public class ErrorStructure<T> {

	private int statuscode;
	private String message;
	private T rootcause;

	public int getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getRootcause() {
		return rootcause;
	}

	public void setRootcause(T rootcause) {
		this.rootcause = rootcause;
	}

	public static <T> ErrorStructure<T> create(int statuscode, String message, T rootCause) {
		ErrorStructure<T> errorStructure = new ErrorStructure<T>();
		errorStructure.setStatuscode(statuscode);
		errorStructure.setMessage(message);
		errorStructure.setRootcause(rootCause);

		return errorStructure;
	}

}
