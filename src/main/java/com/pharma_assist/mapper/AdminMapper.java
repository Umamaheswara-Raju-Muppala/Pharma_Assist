package com.pharma_assist.mapper;

import org.springframework.stereotype.Component;

import com.pharma_assist.entity.Admin;
import com.pharma_assist.requests.AdminRequest;
import com.pharma_assist.responses.AdminResponse;
@Component
public class AdminMapper {
	public Admin adminRequestToAdmin(AdminRequest adminRequest, Admin exAdmin) {
		exAdmin.setAdminEmail(adminRequest.getAdminEmail());
		exAdmin.setAdminPhNo(adminRequest.getAdminPhNo());
		exAdmin.setPassword(adminRequest.getPassword());
		return exAdmin;
	}

	public AdminResponse adminToAdminResponse(Admin admin) {
		return new AdminResponse(admin.getAdminId(), admin.getAdminEmail(), admin.getAdminPhNo());
	}
}
