package com.pharma_assist.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pharma_assist.exceptions.PharmacyNotFoundException;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;

@RestControllerAdvice
public class PharmacyExceptionHandeller {
	private final AppResponseBuilder appResponseBuilder;

	public PharmacyExceptionHandeller(AppResponseBuilder appResponseBuilder) {
		this.appResponseBuilder = appResponseBuilder;
	}

	@ExceptionHandler(PharmacyNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handlePharmacyNotFoundException(
			PharmacyNotFoundException pharmacyNotFoundException) {
		return appResponseBuilder.error(HttpStatus.NOT_FOUND, pharmacyNotFoundException.getMessage(),
				"Pharmacy Not Found");
	}
}
