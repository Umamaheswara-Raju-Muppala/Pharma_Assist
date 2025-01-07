package com.pharma_assist.requests;

import java.time.LocalDate;

import com.pharma_assist.enums.Form;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class MedicineRequest {
	@NotBlank(message = "Name must not be blank")
	private String name;

	@NotBlank(message = "Category must not be blank")
	private String category;

	@Positive(message = "Dosage Must be Greater then 0")
	private int dosageInMg;

	@NotNull(message = "Form must not be null")
	@Enumerated(EnumType.STRING)
	private Form form;

	@NotBlank(message = "Ingredients must not be Blank Or null")
	private String ingredients;

	@NotBlank(message = "manufacturer must not be blank or null")
	private String manufacturer;

	@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
	private double price;

	@NotNull(message = "Expiry date must not be null")
	@Future(message = "Expiry date must be a future date")
	private LocalDate expiryDate;
	@PositiveOrZero(message = "Stock quantity must be 0 or positive")
	private int stockQuantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getDosageInMg() {
		return dosageInMg;
	}

	public void setDosageInMg(int dosageInMg) {
		this.dosageInMg = dosageInMg;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

}
