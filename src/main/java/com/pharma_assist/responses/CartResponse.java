package com.pharma_assist.responses;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CartResponse {

	private String cartId;
	private List<ItemResponse> itemResponses;

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public List<ItemResponse> getItemResponses() {
		return itemResponses;
	}

	public void setItemResponses(List<ItemResponse> itemResponses) {
		this.itemResponses = itemResponses;
	}

}
