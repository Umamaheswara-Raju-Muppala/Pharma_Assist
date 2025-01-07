package com.pharma_assist.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pharma_assist.exceptions.CartNotFoundException;
import com.pharma_assist.exceptions.InsufficientQuantityException;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;

@RestControllerAdvice
public class CartExceptionHandler {
	private final AppResponseBuilder appResponseBuilder;

	public CartExceptionHandler(AppResponseBuilder appResponseBuilder) {
		this.appResponseBuilder = appResponseBuilder;
	}

	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleCartNotFoundException(CartNotFoundException exception) {
		return appResponseBuilder.error(HttpStatus.NOT_FOUND, exception.getMessage(),
				"The Cart you are searching is not found ,Please try again with a valid Cart details");
	}

	@ExceptionHandler(InsufficientQuantityException.class)
	public ResponseEntity<ErrorStructure<String>> InsufficientQuantityExceptionHandler(
			InsufficientQuantityException exception) {
		return appResponseBuilder.error(HttpStatus.BAD_REQUEST, exception.getMessage(),
				"Unable to Add Items into Cart Due to Insuffiecient item Stock");

	}

}
