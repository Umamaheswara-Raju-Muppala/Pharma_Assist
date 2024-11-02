package com.pharma_assist.entity;

import com.pharma_assist.config.GenarateCustomId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pharmacy {
	@Id
	@GenarateCustomId
	private String pharmacyId;
	private String name;
	private String gstNo;
	private String licenceNo;

	public String getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

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
