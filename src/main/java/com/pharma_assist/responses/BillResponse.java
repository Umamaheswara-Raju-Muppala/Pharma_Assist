package com.pharma_assist.responses;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.pharma_assist.enums.PayOptions;

@Component
public class BillResponse {
	private String billId;
	private double gstInPercentage;
	private double grossAmount;
	private double totalPayableAmount;
	private LocalDateTime purchaceTime;
	private PayOptions payOptions;
	private PharmacyResponse pharmacyResponce;
	private PatientResponse patientResponse;
	private CartResponse cartResponse;

	public BillResponse() {

	}

	public BillResponse(String billId, double gstInPercentage, double grossAmount, double totalPayableAmount,
			LocalDateTime purchaceTime, PayOptions payOptions, PharmacyResponse pharmacyResponce,
			PatientResponse patientResponse, CartResponse cartResponse) {
		this.billId = billId;
		this.gstInPercentage = gstInPercentage;
		this.grossAmount = grossAmount;
		this.totalPayableAmount = totalPayableAmount;
		this.purchaceTime = purchaceTime;
		this.payOptions = payOptions;
		this.pharmacyResponce = pharmacyResponce;
		this.patientResponse = patientResponse;
		this.cartResponse = cartResponse;
	}

	public CartResponse getCartResponse() {
		return cartResponse;
	}

	public void setCartResponse(CartResponse cartResponse) {
		this.cartResponse = cartResponse;
	}

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

	public LocalDateTime getPurchaceTime() {
		return purchaceTime;
	}

	public void setPurchaceTime(LocalDateTime purchaceTime) {
		this.purchaceTime = purchaceTime;
	}

	public PayOptions getPayOptions() {
		return payOptions;
	}

	public void setPayOptions(PayOptions payOptions) {
		this.payOptions = payOptions;
	}

	public PharmacyResponse getPharmacyResponce() {
		return pharmacyResponce;
	}

	public void setPharmacyResponce(PharmacyResponse pharmacyResponce) {
		this.pharmacyResponce = pharmacyResponce;
	}

	public PatientResponse getPatientResponse() {
		return patientResponse;
	}

	public void setPatientResponse(PatientResponse patientResponse) {
		this.patientResponse = patientResponse;
	}

}
