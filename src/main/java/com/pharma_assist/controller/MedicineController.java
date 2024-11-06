package com.pharma_assist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pharma_assist.responses.AdminResponse;
import com.pharma_assist.service.MedicineService;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;
import com.pharma_assist.utiliy.SimpleResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
public class MedicineController {
	private final AppResponseBuilder appResponseBuilder;
	private final MedicineService medicineService;

	public MedicineController(AppResponseBuilder appResponseBuilder, MedicineService medicineService) {
		this.appResponseBuilder = appResponseBuilder;
		this.medicineService = medicineService;
	}

	@PostMapping("/pharmacies/{pharmacyId}/medicine")
	@Operation(summary = "Add Medicines", description = "This endpoint allows you to add Medicines to pharmacy.The Pharmacy Id are provided in the path varible.", responses = {
			@ApiResponse(responseCode = "202", description = "Medicines Added"),
			@ApiResponse(responseCode = "404", description = "Pharmacy Not Found", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input provided", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "400", description = "Unavailable Form Enum Type", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	public ResponseEntity<SimpleResponseStructure> uploadMedicines(@Valid @RequestBody MultipartFile file,
			@PathVariable String pharmacyId) {
		String res = medicineService.uploadMedicines(file, pharmacyId);
		return appResponseBuilder.success(HttpStatus.ACCEPTED, "Medicines Added", res);

	}

}
