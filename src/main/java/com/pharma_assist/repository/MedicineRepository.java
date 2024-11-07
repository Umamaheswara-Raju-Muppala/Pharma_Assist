package com.pharma_assist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharma_assist.entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, String> {

	public List<Medicine> findByNameIgnoreCaseContainingOrIngredientsIgnoreCaseContaining(String name,String ingredients);

}
