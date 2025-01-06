package com.pharma_assist.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma_assist.responses.BillResponse;
import com.pharma_assist.service.BillService;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;
import com.pharma_assist.utiliy.ResponseStructure;
import com.pharma_assist.utiliy.SimpleResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class BillController {
	private final BillService billService;
	private final AppResponseBuilder appResponseBuilder;

	public BillController(BillService billService, AppResponseBuilder appResponseBuilder) {
		this.billService = billService;
		this.appResponseBuilder = appResponseBuilder;
	}

	@PostMapping("/bills/{cartId}/{phoneNumber}")
	@Operation(summary = "To Create Bill", description = "This Endpoint allows to create  bill using a valid cartId and patient details", responses = {
			@ApiResponse(responseCode = "404", description = "Cart Not Found or Invalid Cart ID(CartNotFoundException)", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "404", description = "Pharmacy Not Found", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "404", description = "Patient Not Found", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	public ResponseEntity<SimpleResponseStructure> createBill(@PathVariable String cartId,
			@PathVariable String phoneNumber) {
		return appResponseBuilder.success(HttpStatus.CREATED, "Bill Created",
				billService.createBill(cartId, phoneNumber));
	}

	@Operation(summary = "To fetch Bill", description = "This service allows to fetch the bill details using a valid Bill Id", responses = {
			@ApiResponse(responseCode = "302", description = "Bill Found", content = {
					@Content(schema = @Schema(implementation = BillResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Bill Not Found/Invalid Bill Id", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	@GetMapping("bills/{billId}")
	public ResponseEntity<ResponseStructure<BillResponse>> getBill(@PathVariable String billId) {
		return appResponseBuilder.success(HttpStatus.FOUND, "Bill Found", billService.getBill(billId));
	}

	@Operation(summary = "To fetch Bill", description = "This service allows to fetch the bill details using a valid Bill Id", responses = {
			@ApiResponse(responseCode = "200", description = "Bill deleted"),
			@ApiResponse(responseCode = "404", description = "Bill Not Found/Invalid Bill Id", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	@DeleteMapping("bills/{billId}")
	public ResponseEntity<SimpleResponseStructure> deleteBill(@PathVariable String billId) {
		return appResponseBuilder.success(HttpStatus.OK, "Bill deleted", billService.deleteBill(billId));
	}

}
