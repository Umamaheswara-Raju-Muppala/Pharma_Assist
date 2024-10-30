package com.pharma_assist.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pharma_assist.exceptions.AdminNotFoundException;
import com.pharma_assist.exceptions.NoAdminsFoundException;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;

@RestControllerAdvice
public class AdminExceptionHandler {
	private final AppResponseBuilder appResponseBuilder;

	public AdminExceptionHandler(AppResponseBuilder appResponseBuilder) {
		this.appResponseBuilder = appResponseBuilder;
	}

	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleAdminNotFoundException(AdminNotFoundException exception) {
		return appResponseBuilder.error(HttpStatus.NOT_FOUND, exception.getMessage(), "Admin Not Found");
	}

	@ExceptionHandler(NoAdminsFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handlerNoAdminsFoundException(NoAdminsFoundException exception) {
		return appResponseBuilder.error(HttpStatus.NOT_FOUND, exception.getMessage(), "Admins Not Found");
	}

}
