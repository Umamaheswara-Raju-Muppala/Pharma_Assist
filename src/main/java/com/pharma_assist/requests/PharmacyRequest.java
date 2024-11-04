package com.pharma_assist.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PharmacyRequest {
	@NotBlank(message = "Pharmacy name is Required")
	private String name;
	@NotBlank(message = "Pharmacy GST Number is Required ")
	@Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$", message = "GST No must be 15 characters, starting with 2 digits, followed by 5 uppercase letters, 4 digits, 1 uppercase letter, 'Z', and 1 alphanumeric character")
	private String gstNo;
	@NotBlank(message = "Pharmacy license Number is Required ")
	@Pattern(regexp = "^[A-Za-z0-9]{10,15}$", message = "License must be alphanumeric and 10 to 15 characters long")
	private String licenceNo;

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

}
