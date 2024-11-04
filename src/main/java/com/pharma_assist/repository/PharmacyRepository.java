package com.pharma_assist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma_assist.entity.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, String> {
	

}
