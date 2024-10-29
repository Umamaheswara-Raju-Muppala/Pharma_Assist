package com.pharma_assist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
