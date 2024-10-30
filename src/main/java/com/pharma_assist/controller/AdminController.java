package com.pharma_assist.controller;

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
import com.pharma_assist.utiliy.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class AdminController {
	private final AdminService adminService;
	private final AppResponseBuilder appResponseBuilder;

	public AdminController(AdminService adminService, AppResponseBuilder appResponseBuilder) {
		this.adminService = adminService;
		this.appResponseBuilder = appResponseBuilder;
	}

	@PostMapping("admin")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@Valid @RequestBody AdminRequest adminRequest) {
		AdminResponse adminResponse = adminService.addAdmin(adminRequest);
		return appResponseBuilder.success(HttpStatus.CREATED, "Admin Added", adminResponse);
	}

	@PutMapping("admin/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@RequestBody AdminRequest adminRequest,
			@PathVariable String adminId) {
		AdminResponse adminResponse = adminService.updateAdmin(adminRequest, adminId);
		return appResponseBuilder.success(HttpStatus.OK, "Admin Updated", adminResponse);
	}

	@GetMapping("admin/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(@PathVariable String adminId) {
		AdminResponse adminResponse = adminService.findAdmin(adminId);
		return appResponseBuilder.success(HttpStatus.FOUND, "Admin Found", adminResponse);
	}

}
