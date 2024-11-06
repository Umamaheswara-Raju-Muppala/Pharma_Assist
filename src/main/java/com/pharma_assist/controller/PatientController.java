package com.pharma_assist.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pharma_assist.requests.PatientRequest;
import com.pharma_assist.responses.AdminResponse;
import com.pharma_assist.responses.PatientResponse;
import com.pharma_assist.service.PatientService;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;
import com.pharma_assist.utiliy.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
public class PatientController {
	public final PatientService patientService;
	public final AppResponseBuilder appResponseBuilder;

	public PatientController(PatientService patientService, AppResponseBuilder appResponseBuilder) {
		super();
		this.patientService = patientService;
		this.appResponseBuilder = appResponseBuilder;
	}

	@PostMapping("/pharmacies/{pharmacyId}/patients")
	@Operation(summary = "Add a new Patient", description = "This endpoint allows you to add a new Patient", responses = {
			@ApiResponse(responseCode = "201", description = "Patient Added Successfully", content = {
					@Content(schema = @Schema(implementation = AdminResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input provided", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "404", description = "Pharmacy Not Found", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	public ResponseEntity<ResponseStructure<PatientResponse>> addPatient(
			@Valid @RequestBody PatientRequest patientRequest, @PathVariable String pharmacyId) {
		PatientResponse patientResponse = patientService.addPatient(patientRequest, pharmacyId);
		return appResponseBuilder.success(HttpStatus.CREATED, "Patient Created", patientResponse);
	}
}
