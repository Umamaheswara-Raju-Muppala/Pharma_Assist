package com.pharma_assist.entity;

import com.pharma_assist.config.GenarateCustomId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Admin {
	@Id
	@GenarateCustomId
	private String adminId;
	private String adminEmail;
	private String adminPhNo;
	private String password;

	@OneToOne
	private Pharmacy pharmacy;

	public Admin() {
	}

	public Admin(String adminEmail, String adminPhNo, String password) {

		this.adminEmail = adminEmail;
		this.adminPhNo = adminPhNo;
		this.password = password;
	}

	public String getAdminId() {
		return adminId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

}
