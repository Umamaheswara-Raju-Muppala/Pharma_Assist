package com.pharma_assist.service;

import org.springframework.stereotype.Service;

import com.pharma_assist.entity.Cart;
import com.pharma_assist.repository.CartRepository;

@Service
public class CartService {

	private final CartRepository cartRepository;

	public CartService(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	public String createCart() {
		Cart cart = new Cart();
		cartRepository.save(cart);
		return "CartID : "+cart.getCartId();
	}

}
