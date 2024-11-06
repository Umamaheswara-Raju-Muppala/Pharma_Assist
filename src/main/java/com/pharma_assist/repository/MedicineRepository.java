package com.pharma_assist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pharma_assist.entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, String> {

	@Query("select m from Medicine m where m.name=:name or m.dosageInMg=:dosageInMg")
	public List<Medicine> findMedicineByNameOrDosage(String name, int dosageInMg);

}
