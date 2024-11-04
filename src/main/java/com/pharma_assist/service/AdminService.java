package com.pharma_assist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pharma_assist.entity.Admin;
import com.pharma_assist.exceptions.AdminNotFoundException;
import com.pharma_assist.exceptions.NoAdminsFoundException;
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
				.map(adminRepository::save).map(adminMapper::adminToAdminResponse)
				.orElseThrow(() -> new AdminNotFoundException("Admin Not Found"));
	}

	public AdminResponse findAdmin(String adminId) {
		return adminRepository.findById(adminId).map(adminMapper::adminToAdminResponse)
				.orElseThrow(() -> new AdminNotFoundException("Admin Not Found"));

	}

	public List<AdminResponse> findAdmins() {
		List<Admin> admin = adminRepository.findAll();
		if (admin.isEmpty()) {
			new NoAdminsFoundException("Admins Not Found");
		}
		return admin.stream().map(adminMapper::adminToAdminResponse).toList();
	}
}
