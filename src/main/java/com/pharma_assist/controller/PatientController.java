package com.pharma_assist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/pharmacies/{pharmacyId}")
	@Operation(summary = "Fetch All Patients in Pharmacy", description = "This endpoint can be used to fetch all Patients in pharmacy using pharmacy id", responses = {
			@ApiResponse(responseCode = "302", description = "Patients Found", content = {
					@Content(schema = @Schema(implementation = AdminResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Patients Not Found", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	public ResponseEntity<ResponseStructure<List<PatientResponse>>> findAllPatientsByPharmacyId(
			@PathVariable String pharmacyId) {
		List<PatientResponse> patients = patientService.findAllPatientsByPharmacyId(pharmacyId);
		return appResponseBuilder.success(HttpStatus.FOUND, "Patients Found", patients);
	}

	@PutMapping("/patients/{patientId}")
	@Operation(summary = "Update Existing Patient", description = "This endpoint allows you to update the patient using an patient id as a parameter and patient details as Request body.", responses = {
			@ApiResponse(responseCode = "200", description = "Patient Updated", content = {
					@Content(schema = @Schema(implementation = AdminResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Patient Not Found", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "500", description = "Invalid input Provided", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	public ResponseEntity<ResponseStructure<PatientResponse>> updatePatientByPatientId(
			@Valid @RequestBody PatientRequest patientRequest, @PathVariable String patientId) {
		PatientResponse patientResponse = patientService.updatePatientByPatientId(patientRequest, patientId);
		return appResponseBuilder.success(HttpStatus.OK, "Patient Updated", patientResponse);
	}
}
