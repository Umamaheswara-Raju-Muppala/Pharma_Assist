package com.pharma_assist.exception_handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pharma_assist.utiliy.ErrorStructure;

@RestControllerAdvice
public class FeildErrorExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ObjectError> errors = exception.getAllErrors();
		List<FeildErrorStructure> errorResponses = new ArrayList<FeildErrorStructure>();
		for (ObjectError error : errors) {
			FieldError fieldError = (FieldError) error;
			String message = fieldError.getDefaultMessage();
			String field = fieldError.getField();
			Object rejectedValue = fieldError.getRejectedValue();
			FeildErrorStructure feildErrorStructure = new FeildErrorStructure(message, field, rejectedValue);
			errorResponses.add(feildErrorStructure);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorStructure.create(HttpStatus.BAD_REQUEST.value(), "Invalid Input", errorResponses));

	}

	class FeildErrorStructure {
		private String message;
		private String field;
		private Object rejectedValue;

		public FeildErrorStructure(String message, String feild, Object rejectedValue) {
			this.message = message;
			this.field = feild;
			this.rejectedValue = rejectedValue;
		}

		public String getMessage() {
			return message;
		}

		public String getField() {
			return field;
		}

		public Object getRejectedValue() {
			return rejectedValue;
		}

	}

}