package com.pharma_assist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharma_assist.service.CartService;
import com.pharma_assist.utiliy.AppResponseBuilder;
import com.pharma_assist.utiliy.ErrorStructure;
import com.pharma_assist.utiliy.SimpleResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

}
