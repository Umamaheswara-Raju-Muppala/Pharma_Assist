package com.pharma_assist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

}
