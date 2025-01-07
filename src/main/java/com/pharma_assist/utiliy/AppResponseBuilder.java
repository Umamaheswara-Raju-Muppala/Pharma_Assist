package com.pharma_assist.utiliy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AppResponseBuilder {

	public <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, String message, T data) {
		return ResponseEntity.status(status).body(ResponseStructure.create(status.value(), message, data));
	}

	public <T> ResponseEntity<ErrorStructure<T>> error(HttpStatus status, String message, T rootcause) {
		return ResponseEntity.status(status).body(ErrorStructure.create(status.value(), message, rootcause));
	}

	public ResponseEntity<SimpleResponseStructure> success(HttpStatus httpStatus, String message, String status) {
		return ResponseEntity.status(httpStatus).body(SimpleResponseStructure.create(message, status));
	}

}
