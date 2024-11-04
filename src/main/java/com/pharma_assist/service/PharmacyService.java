package com.pharma_assist.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pharma_assist.entity.Admin;
import com.pharma_assist.entity.Pharmacy;
import com.pharma_assist.exceptions.AdminNotFoundException;
import com.pharma_assist.mapper.PharmacyMapper;
import com.pharma_assist.repository.AdminRepository;
import com.pharma_assist.repository.PharmacyRepository;
import com.pharma_assist.requests.PharmacyRequest;
import com.pharma_assist.responses.PharmacyResponse;

@Service
public class PharmacyService {
	private final PharmacyMapper pharmacyMapper;
	private final PharmacyRepository pharmacyRepository;
	private final AdminRepository adminRepository;
	// private final AdminMapper adminMapper;

	public PharmacyService(PharmacyMapper pharmacyMapper, PharmacyRepository pharmacyRepository,
			AdminRepository adminRepository) {
		this.pharmacyMapper = pharmacyMapper;
		this.pharmacyRepository = pharmacyRepository;
		this.adminRepository = adminRepository;
	}

	public PharmacyResponse addPharmacy(PharmacyRequest pharmacyRequest, String adminId) {
		Pharmacy pharmacy = pharmacyMapper.pharmacyRequestToPharmacy(pharmacyRequest, new Pharmacy());
		pharmacyRepository.save(pharmacy);
		adminRepository.findById(adminId).ifPresentOrElse(admin -> {
			admin.setPharmacy(pharmacy);
			adminRepository.save(admin);
		}, () -> {
			throw new AdminNotFoundException("Admin Not Found");
		});
		return pharmacyMapper.pharmacyToPharmacyResponse(pharmacy);
	}

}