package com.pharma_assist.responses;

import com.pharma_assist.entity.Pharmacy;

public class AdminResponse {
	private String adminId;
	private String adminEmail;
	private String adminPhNo;

	private Pharmacy pharmacy;

	public AdminResponse() {

	}

	public AdminResponse(String adminId, String adminEmail, String adminPhNo) {
		super();
		this.adminId = adminId;
		this.adminEmail = adminEmail;
		this.adminPhNo = adminPhNo;

	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPhNo() {
		return adminPhNo;
	}

	public void setAdminPhNo(String adminPhNo) {
		this.adminPhNo = adminPhNo;
	}

}
