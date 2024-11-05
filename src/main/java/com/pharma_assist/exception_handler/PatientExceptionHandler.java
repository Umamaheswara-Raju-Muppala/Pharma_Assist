package com.pharma_assist.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pharma_assist.exceptions.NoPatientsFoundException;
import com.pharma_assist.exceptions.PatientNotFoundException;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;

@RestControllerAdvice
public class PatientExceptionHandler {
	private final AppResponseBuilder appResponseBuilder;

	public PatientExceptionHandler(AppResponseBuilder appResponseBuilder) {
		super();
		this.appResponseBuilder = appResponseBuilder;
	}

	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handlePatientNotFoundException(PatientNotFoundException exception) {
		return appResponseBuilder.error(HttpStatus.NOT_FOUND, exception.getMessage(), "Patient Not Found");
	}

	@ExceptionHandler(NoPatientsFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleNoPatientsFoundException(NoPatientsFoundException exception) {
		return appResponseBuilder.error(HttpStatus.NOT_FOUND, exception.getMessage(), "Patients Not Found");
	}

}
