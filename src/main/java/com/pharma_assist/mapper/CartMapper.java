package com.pharma_assist.mapper;

import org.springframework.stereotype.Component;

import com.pharma_assist.entity.Cart;
import com.pharma_assist.responses.CartResponse;

@Component
public class CartMapper {
	private final ItemMapper itemMapper;

	public CartMapper(ItemMapper itemMapper) {
		this.itemMapper = itemMapper;
	}

	public CartResponse CartToCartResponse(Cart cart) {
		CartResponse cartResponse = new CartResponse();
		cartResponse.setCartId(cart.getCartId());
		cart.getItems().stream().map(itemMapper::itemToItemResponse)
				.map((itemResponse) -> cartResponse.getItemResponses().add(itemResponse));
		return cartResponse;
	}

}
