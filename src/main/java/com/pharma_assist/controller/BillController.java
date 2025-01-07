package com.pharma_assist.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pharma_assist.entity.Bill;
import com.pharma_assist.enums.PayOptions;
import com.pharma_assist.responses.BillResponse;
import com.pharma_assist.service.BillService;
import com.pharma_assist.service.PdfService;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;
import com.pharma_assist.utiliy.ResponseStructure;
import com.pharma_assist.utiliy.SimpleResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class BillController {
	private final BillService billService;
	private final AppResponseBuilder appResponseBuilder;
	private final PdfService pdfService;

	public BillController(BillService billService, AppResponseBuilder appResponseBuilder, PdfService pdfService) {
		this.billService = billService;
		this.appResponseBuilder = appResponseBuilder;
		this.pdfService = pdfService;
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

	@Operation(summary = "Confirm Bill", description = "This Endpoint is used to confirm the bill", responses = {
			@ApiResponse(responseCode = "200", description = "Bill Generated Successfully", content = {
					@Content(schema = @Schema(implementation = BillResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Bill Not Found/Invalid Bill Id", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })

	@PostMapping("bills/{billId}/confirm")
	public ResponseEntity<ResponseStructure<BillResponse>> confirmBill(@PathVariable String billId,
			@RequestParam PayOptions paymentType) {
		return appResponseBuilder.success(HttpStatus.OK, "Bill Generated Successfully",
				billService.confirmBill(billId, paymentType));

	}

	@Operation(summary = "Confirm Bill", description = "This Endpoint is used to confirm the bill", responses = {
			@ApiResponse(responseCode = "404", description = "Bill Not Found/Invalid Bill Id", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	@GetMapping("bills/{billId}/generate")
	public void generateBillPdf(@PathVariable String billId, HttpServletResponse response) throws Exception {
		Bill bill = billService.fetchBill(billId);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("billId", bill.getBillId());
		data.put("patientName", bill.getPatient().getName());
		data.put("pharmacyName", bill.getPharmacy().getName());

		List<Map<String, Object>> items = bill.getCart().getItems().stream().map(item -> {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", item.getName());
			map.put("quantity", item.getQuantity());
			map.put("dosage", item.getDosage());
			map.put("itemPrice", item.getItemPrice());
			map.put("price", item.getTotalPrice());
			return map;
		}).collect(Collectors.toList());
		data.put("items", items);
		data.put("purchaseTime", bill.getPurchaseTime());
		data.put("paymentType", bill.getPayOptions());
		data.put("grossAmount", bill.getGrossAmount());
		data.put("totalPayableAmount", bill.getTotalPayableAmount());
		byte[] pdfBytes = pdfService.generatePdf("Pharmacy-Bill", data);
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=Pharmacy-Bill.pdf");
		response.getOutputStream().write(pdfBytes);

	}
}
