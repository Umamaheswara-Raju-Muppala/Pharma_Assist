package com.pharma_assist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma_assist.entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, String> {

}
