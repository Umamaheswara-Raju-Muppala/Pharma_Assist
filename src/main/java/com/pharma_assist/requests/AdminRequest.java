package com.pharma_assist.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AdminRequest {
	@NotNull(message = "email id cannot be null")
	@NotBlank(message = "email id cannot be empty")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Invalid email id")
	private String adminEmail;
	@Pattern(regexp = "^(6|7|8|9)[0-9]{9}$", message = "Phone number must be between 6000000000 and 9999999999")
	private String adminPhNo;
	@NotNull(message = "password cannot be null")
	@NotBlank(message = "password is manditory")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,12}$", message = "Password must be 8 to 12 characters long, contain at least one uppercase letter, one lowercase letter, one digit, and one special character (e.g., !@#$%^&*).\r\n"
			+ "")
	private String password;

	public AdminRequest() {

	}

	public AdminRequest(String adminEmail, String adminPhNo, String password) {
		super();
		this.adminEmail = adminEmail;
		this.adminPhNo = adminPhNo;
		this.password = password;
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

}
