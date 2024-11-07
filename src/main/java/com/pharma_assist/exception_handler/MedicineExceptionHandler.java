package com.pharma_assist.exception_handler;

import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pharma_assist.exceptions.InvalidFormException;
import com.pharma_assist.exceptions.NoMedicinesFoundException;
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

	@ExceptionHandler(NoMedicinesFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleNoMedicinesFoundException(NoMedicinesFoundException exception) {
		return appResponseBuilder.error(HttpStatus.NOT_FOUND, exception.getMessage(), "No Medicines Found");
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ErrorStructure<String>> handleIllegalStateException(IllegalStateException exception) {
		return appResponseBuilder.error(HttpStatus.BAD_REQUEST, exception.getMessage(), "Invalid Input Provided");
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorStructure<String>> handleNullPointerException(NullPointerException exception) {
		return appResponseBuilder.error(HttpStatus.BAD_REQUEST, exception.getMessage(), "Invalid Input Provided");
	}

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<ErrorStructure<String>> handleDateTimeParseException(DateTimeParseException exception) {
		return appResponseBuilder.error(HttpStatus.BAD_REQUEST, exception.getMessage(), "Invalid Input Provided");
	}
}
