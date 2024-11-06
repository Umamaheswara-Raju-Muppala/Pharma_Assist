package com.pharma_assist.entity;

import java.util.List;

import com.pharma_assist.config.GenarateCustomId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Pharmacy {
	@Id
	@GenarateCustomId
	private String pharmacyId;
	private String name;
	private String gstNo;
	private String licenceNo;
	@OneToMany(mappedBy = "pharmacy")
	private List<Patient> patient;
	@OneToMany(mappedBy="pharmacy")
	private List<Medicine> medicine;

	public String getPharmacyId() {
		return pharmacyId;
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

	public List<Patient> getPatient() {
		return patient;
	}

	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}

	public List<Medicine> getMedicine() {
		return medicine;
	}

	public void setMedicine(List<Medicine> medicine) {
		this.medicine = medicine;
	}
	

}
