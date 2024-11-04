package com.pharma_assist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pharma_assist.entity.Admin;
import com.pharma_assist.entity.Pharmacy;

public interface AdminRepository extends JpaRepository<Admin, String> {
	@Query("select a.pharmacy from Admin a where a.adminId=:adminId")
	Optional<Pharmacy> findPharmacyByAdminId(String adminId);

}
