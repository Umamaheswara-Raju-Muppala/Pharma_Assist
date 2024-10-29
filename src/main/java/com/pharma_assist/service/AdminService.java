package com.pharma_assist.service;

import org.springframework.stereotype.Service;

import com.pharma_assist.entity.Admin;
import com.pharma_assist.exceptions.AdminNotFoundException;
import com.pharma_assist.mapper.AdminMapper;
import com.pharma_assist.repository.AdminRepository;
import com.pharma_assist.requests.AdminRequest;
import com.pharma_assist.responses.AdminResponse;

@Service
public class AdminService {
	private final AdminMapper adminMapper;
	private final AdminRepository adminRepository;

	public AdminService(AdminMapper adminMapper, AdminRepository adminRepository) {
		this.adminMapper = adminMapper;
		this.adminRepository = adminRepository;

	}

	public AdminResponse addAdmin(AdminRequest adminRequest) {
		Admin admin = adminRepository.save(adminMapper.adminRequestToAdmin(adminRequest, new Admin()));
		return adminMapper.adminToAdminResponse(admin);
	}

	public AdminResponse updateAdmin(AdminRequest adminRequest, String adminId) {
		return adminRepository.findById(adminId).map((exUser) -> adminMapper.adminRequestToAdmin(adminRequest, exUser))
				.map(adminMapper::adminToAdminResponse)
				.orElseThrow(() -> new AdminNotFoundException("Admin Not Found"));
	}
}
