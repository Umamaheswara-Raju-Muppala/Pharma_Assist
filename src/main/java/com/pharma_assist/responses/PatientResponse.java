package com.pharma_assist.responses;

import java.time.LocalDate;

import com.pharma_assist.enums.Gender;

public class PatientResponse {
	private String patientId;
	private String name;
	private String phoneNumber;
	private String email;
	private Gender gender;
	private LocalDate dateOfBirth;

	public PatientResponse() {

	}

	public PatientResponse(String patientId, String name, String phoneNumber, String email, Gender gender,
			LocalDate dateOfBirth) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
