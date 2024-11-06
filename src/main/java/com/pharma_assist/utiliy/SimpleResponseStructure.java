package com.pharma_assist.utiliy;

public class SimpleResponseStructure {
	private String status;
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static SimpleResponseStructure create(String status, String message) {
		SimpleResponseStructure structure = new SimpleResponseStructure();
		structure.setMessage(message);
		structure.setStatus(status);
		return structure;
	}
}
