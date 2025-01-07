package com.pharma_assist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pharma_assist.responses.CartResponse;
import com.pharma_assist.service.CartService;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;
import com.pharma_assist.utiliy.ResponseStructure;
import com.pharma_assist.utiliy.SimpleResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class CartController {
	private final AppResponseBuilder appResponseBuilder;
	private final CartService cartService;

	public CartController(AppResponseBuilder appResponseBuilder, CartService cartService) {
		this.appResponseBuilder = appResponseBuilder;
		this.cartService = cartService;
	}

	@PostMapping("/cart")
	@Operation(summary = "Create a Cart", description = "This end point let us create a Cart for the User to Add Items", responses = {
			@ApiResponse(responseCode = "201", description = "Cart Added"),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	public ResponseEntity<SimpleResponseStructure> createCart() {
		return appResponseBuilder.success(HttpStatus.CREATED, "Cart Created", cartService.createCart());
	}

	@PostMapping("/cart/{cartId}/medicine/{medicineId}/add")
	@Operation(summary = "Add Items into Cart", description = "This endpoint allows us to add Items(Medicines) into a Cart", responses = {

			@ApiResponse(responseCode = "201", description = "Item Added into Cart", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "404", description = "Cart Not Found or Invalid Cart ID(CartNotFoundException)", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "404", description = "Medicine Found or Invalid Medicine Id(MedicineNotFoundException) ", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "400", description = "Insufficieant Stock(InsufficientStockQuantity). Available {Existed Stock Quantity} Please choose less then or equal quantity that exits in stock", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })

	public ResponseEntity<SimpleResponseStructure> addItemIntoCart(@PathVariable String cartId,
			@PathVariable String medicineId, @RequestParam int quantity) {
		return appResponseBuilder.success(HttpStatus.CREATED, "Item Added into Cart",
				cartService.addItemIntoCart(cartId, medicineId, quantity));

	}

	@Operation(summary = "Remove Items from Cart", description = "This endpoint allows us to Remove Items(Medicines) from a Cart", responses = {

			@ApiResponse(responseCode = "200", description = "Item Removed from Cart", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "404", description = "Cart Not Found or Invalid Cart ID(CartNotFoundException)", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "404", description = "Medicine Found or Invalid Medicine Id(MedicineNotFoundException) ", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	@PostMapping("cart/{cartId}/medicine/{medicineId}/remove")
	public ResponseEntity<SimpleResponseStructure> removeItemFromCart(@PathVariable String cartId,
			@PathVariable String medicineId) {
		return appResponseBuilder.success(HttpStatus.OK, "Item Removed from Cart",
				cartService.removeItemFromCart(cartId, medicineId));
	}

	@Operation(summary = "Fetch Cart Details", description = "This endpoint used to  fetch the Items in a Cart", responses = {
			@ApiResponse(responseCode = "302", description = "Cart Found", content = {
					@Content(schema = @Schema(implementation = ResponseStructure.class)) }),
			@ApiResponse(responseCode = "404", description = "Cart Not Found or Invalid Cart ID(CartNotFoundException)", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class)) }) })
	@GetMapping("/cart/{cartId}")
	public ResponseEntity<ResponseStructure<CartResponse>> getCart(@PathVariable String cartId) {
		return appResponseBuilder.success(HttpStatus.FOUND, "Cart Found", cartService.getCart(cartId));
	}
}
