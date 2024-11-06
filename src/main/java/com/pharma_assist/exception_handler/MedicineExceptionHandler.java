package com.pharma_assist.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pharma_assist.exceptions.InvalidFormException;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;

@RestControllerAdvice
public class MedicineExceptionHandler {
	private final AppResponseBuilder appResponseBuilder;

	public MedicineExceptionHandler(AppResponseBuilder appResponseBuilder) {
		this.appResponseBuilder = appResponseBuilder;
	}

	@ExceptionHandler(InvalidFormException.class)
	public ResponseEntity<ErrorStructure<String>> handleInvalidFormException(InvalidFormException exception) {
		return appResponseBuilder.error(HttpStatus.BAD_REQUEST, exception.getMessage(), "Unavailable Form Enum Type");
	}

}
