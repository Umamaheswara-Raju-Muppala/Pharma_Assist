package com.pharma_assist.entity;

import java.util.List;

import com.pharma_assist.config.GenarateCustomId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cart {

	@Id
	@GenarateCustomId
	private String cartId;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<Item> items;

	public void addItem(Item item) {
		this.items.add(item);
		item.setCart(this);

	}

	public void removeItem(Item item) {
		this.items.remove(item);
		item.setCart(null);
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
