package com.pharma_assist.requests;

import java.time.LocalDate;

import com.pharma_assist.enums.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PatientRequest {
	@NotBlank(message = "Name cannot be blank")
	private String name;
	@NotBlank(message = "Phone number must not be blank")
	@Pattern(regexp = "^(6|7|8|9)[0-9]{9}$", message = "Phone number must be between 6000000000 and 9999999999")
	private String phoneNumber;
	@NotBlank(message = "email id cannot be empty")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Invalid email id")
	private String email;
	@NotNull(message = "Gender must not be null")
	private Gender gender;
	@NotNull(message = "Date of birth must not be null")

	private LocalDate dateOfBirth;

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
