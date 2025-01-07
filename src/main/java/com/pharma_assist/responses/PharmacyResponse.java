package com.pharma_assist.responses;

public class PharmacyResponse {

	private String pharmacyId;
	private String name;
	private String gstNo;
	private String licenceNo;

	public PharmacyResponse() {

	}

	public PharmacyResponse(String pharmacyId, String name, String gstNo, String licenceNo) {
		this.pharmacyId = pharmacyId;
		this.name = name;
		this.gstNo = gstNo;
		this.licenceNo = licenceNo;
	}

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
