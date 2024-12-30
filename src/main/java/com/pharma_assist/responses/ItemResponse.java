package com.pharma_assist.responses;

import org.springframework.stereotype.Component;

@Component
public class ItemResponse {

	private String itemId;
	private String name;
	private String dosage;
	private Integer quantity;
	private double itemPrice;
	private double totalPrice;

	public ItemResponse() {
	}

	public ItemResponse(String itemId, String name, String dosage, Integer quantity, double itemPrice,
			double totalPrice) {
		this.itemId = itemId;
		this.name = name;
		this.dosage = dosage;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
		this.totalPrice = totalPrice;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
