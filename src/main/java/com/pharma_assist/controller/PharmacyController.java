package com.pharma_assist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pharma_assist.requests.PharmacyRequest;
import com.pharma_assist.responses.AdminResponse;
import com.pharma_assist.responses.PharmacyResponse;
import com.pharma_assist.service.PharmacyService;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;
import com.pharma_assist.utiliy.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
public class PharmacyController {
	private final PharmacyService pharmacyService;
	private final AppResponseBuilder appResponseBuilder;

	public PharmacyController(PharmacyService pharmacyService, AppResponseBuilder appResponseBuilder) {
		this.appResponseBuilder = appResponseBuilder;
		this.pharmacyService = pharmacyService;
	}

	@Operation(summary = "Add Pharamacy Into Admin", description = "This Endpoint allows to Add Pharmacy into Admin.", responses = {
			@ApiResponse(responseCode = "201", description = "Admin Added Successfully", content = {
					@Content(schema = @Schema(implementation = AdminResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Admin Not Found", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input provided", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	@PostMapping("/admins/{adminId}/pharmacies")
	public ResponseEntity<ResponseStructure<PharmacyResponse>> addPharmacy(
			@Valid @RequestBody PharmacyRequest pharmacyRequest, @PathVariable String adminId) {
		PharmacyResponse pharmacyResponse = pharmacyService.addPharmacy(pharmacyRequest, adminId);
		return appResponseBuilder.success(HttpStatus.CREATED, "Pharmacy Added Into Admin", pharmacyResponse);
	}

	@GetMapping("/admins/{adminId}/pharmacy")
	@Operation(summary = "Find Pharmacy ", description = "This endpoint allows can fetch the Pharmacy using Admin Id", responses = {
			@ApiResponse(responseCode = "302", description = "Pharmacy Found", content = {
					@Content(schema = @Schema(implementation = PharmacyResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Admin Not Found", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "404", description = "Pharmacy Not Found", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	public ResponseEntity<ResponseStructure<PharmacyResponse>> findPharmacyByAdminId(@PathVariable String adminId) {
		PharmacyResponse pharmacyResponse = pharmacyService.findPharmacyByAdminId(adminId);
		return appResponseBuilder.success(HttpStatus.FOUND, "Pharmacy Found", pharmacyResponse);

	}

	@Operation(summary = "Update Pharmacy", description = "This endpoint allows to update the pharmacy using pharmacy Id", responses = {
			@ApiResponse(responseCode = "200", description = "Pharmacy Updated", content = {
					@Content(schema = @Schema(implementation = PharmacyResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Pharmacy Not Found", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input provided", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	@PutMapping("/pharmacies/{pharmacyId}")
	public ResponseEntity<ResponseStructure<PharmacyResponse>> UpdatePharmacyByPharmacyId(
			@Valid @RequestBody PharmacyRequest pharmacyRequest, @PathVariable String pharmacyId) {
		PharmacyResponse pharmacyResponse = pharmacyService.UpdatePharmacyByPharmacyId(pharmacyRequest, pharmacyId);
		return appResponseBuilder.success(HttpStatus.OK, "Pharmacy Updated", pharmacyResponse);
	}

}
