package com.pharma_assist.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pharma_assist.exceptions.BillNotFoundException;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;

@RestControllerAdvice
public class BillExceptionHandler {
	private AppResponseBuilder appResponseBuilder;

	public BillExceptionHandler(AppResponseBuilder appResponseBuilder) {
		this.appResponseBuilder = appResponseBuilder;
	}

	@ExceptionHandler(BillNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleBillNotFoundException(BillNotFoundException exception) {
		return appResponseBuilder.error(HttpStatus.NOT_FOUND, exception.getMessage(),
				"Bill Not Found With Corresponding Bill id Try agian!! using a valid id");
	}
}
