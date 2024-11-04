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

import com.pharma_assist.requests.AdminRequest;
import com.pharma_assist.responses.AdminResponse;
import com.pharma_assist.service.AdminService;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;
import com.pharma_assist.utiliy.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
public class AdminController {
	private final AdminService adminService;
	private final AppResponseBuilder appResponseBuilder;

	public AdminController(AdminService adminService, AppResponseBuilder appResponseBuilder) {
		this.adminService = adminService;
		this.appResponseBuilder = appResponseBuilder;
	}

	@PostMapping("admins")
	@Operation(summary = "Add a new admin", description = "This endpoint allows you to add a new admin. The admin details are provided in the request body.", responses = {
			@ApiResponse(responseCode = "201", description = "Admin Added Successfully", content = {
					@Content(schema = @Schema(implementation = AdminResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input provided", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(@Valid @RequestBody AdminRequest adminRequest) {
		AdminResponse adminResponse = adminService.addAdmin(adminRequest);
		return appResponseBuilder.success(HttpStatus.CREATED, "Admin Added", adminResponse);
	}

	@PutMapping("admins/{adminId}")
	@Operation(summary = "Update Existing Admin", description = "This endpoint allows you to update the admin using an admin id as a parameter and admin details as Reqest body.", responses = {
			@ApiResponse(responseCode = "200", description = "Admin Updated", content = {
					@Content(schema = @Schema(implementation = AdminResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Admin Not Found", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "500", description = "Invalid input Provided", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@RequestBody AdminRequest adminRequest,
			@PathVariable String adminId) {
		AdminResponse adminResponse = adminService.updateAdmin(adminRequest, adminId);
		return appResponseBuilder.success(HttpStatus.OK, "Admin Updated", adminResponse);
	}

	@GetMapping("admins/{adminId}")
	@Operation(summary = "Fetch Admin by Unique Admin Id", description = "This endpoint can fetch admin using admin id as a parameter", responses = {
			@ApiResponse(responseCode = "302", description = "Admins Found", content = {
					@Content(schema = @Schema(implementation = AdminResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Admins Not Found", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })

	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(@PathVariable String adminId) {
		AdminResponse adminResponse = adminService.findAdmin(adminId);
		return appResponseBuilder.success(HttpStatus.FOUND, "Admin Found", adminResponse);
	}

	@GetMapping("admins")
	@Operation(summary = "Fetch All Admins", description = "This endpoint can be used to fetch all admins", responses = {
			@ApiResponse(responseCode = "302", description = "Admins Found", content = {
					@Content(schema = @Schema(implementation = AdminResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Admins Not Found", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAdmins() {
		List<AdminResponse> adminResponses = adminService.findAdmins();
		return appResponseBuilder.success(HttpStatus.FOUND, "Admins Found", adminResponses);

	}
}
