package com.pharma_assist.entity;

import java.time.LocalDateTime;

import com.pharma_assist.config.GenarateCustomId;
import com.pharma_assist.enums.PayOptions;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Bill {

	@Id
	@GenarateCustomId
	private String billId;
	private double gstInPercentage;
	private double grossAmount;
	private double totalPayableAmount;
	private LocalDateTime purchaceTime;
	@Enumerated(EnumType.STRING)
	private PayOptions payOptions;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pharmacy_id", nullable = false)
	private Pharmacy pharmacy;

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public double getGstInPercentage() {
		return gstInPercentage;
	}

	public void setGstInPercentage(double gstInPercentage) {
		this.gstInPercentage = gstInPercentage;
	}

	public double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public double getTotalPayableAmount() {
		return totalPayableAmount;
	}

	public void setTotalPayableAmount(double totalPayableAmount) {
		this.totalPayableAmount = totalPayableAmount;
	}

	public PayOptions getPayOptions() {
		return payOptions;
	}

	public void setPayOptions(PayOptions payOptions) {
		this.payOptions = payOptions;
	}

	public Patient getPatient() {
		return patient;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public LocalDateTime getPurchaceTime() {
		return purchaceTime;
	}

	public void setPurchaceTime(LocalDateTime purchaceTime) {
		this.purchaceTime = purchaceTime;
	}

}
