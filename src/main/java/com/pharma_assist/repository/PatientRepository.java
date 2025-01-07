package com.pharma_assist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma_assist.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

	public Optional<Patient> findByPhoneNumber(String phoneNumber);

}
