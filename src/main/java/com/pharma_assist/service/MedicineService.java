package com.pharma_assist.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.pharma_assist.enums.Form;
import com.pharma_assist.exceptions.NoMedicinesFoundException;
import com.pharma_assist.exceptions.PharmacyNotFoundException;
import com.pharma_assist.entity.Medicine;
import com.pharma_assist.entity.Pharmacy;
import com.pharma_assist.mapper.MedicineMapper;
import com.pharma_assist.repository.MedicineRepository;
import com.pharma_assist.repository.PharmacyRepository;
import com.pharma_assist.responses.MedicineResponse;

import jakarta.validation.Valid;

@Service
@Transactional
public class MedicineService {
	private final MedicineRepository medicineRepository;
	private final MedicineMapper medicineMapper;
	private final PharmacyRepository pharmacyRepository;

	public MedicineService(MedicineRepository medicineRepository, MedicineMapper medicineMapper,
			PharmacyRepository pharmacyRepository) {
		this.medicineRepository = medicineRepository;
		this.medicineMapper = medicineMapper;
		this.pharmacyRepository = pharmacyRepository;
	}

	public String uploadMedicines(MultipartFile file, String pharmacyId) {
		Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
				.orElseThrow(() -> new PharmacyNotFoundException("Pharmacy Not Found"));

		try (XSSFWorkbook workBook = new XSSFWorkbook(file.getInputStream())) {
			for (Sheet sheet : workBook) {
				for (Row row : sheet) {
					if (row.getRowNum() != 0) {

						Medicine medicine = validatedMedicines(row);
						medicine.setPharmacy(pharmacy);
						saveMedicine(medicine);

					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Medicines added Suucessfully";
	}

	public List<MedicineResponse> findByNameLikeIgnoreCaseOrIngredientsLikeIgnoreCase(String name) {
		List<Medicine> medicines = medicineRepository
				.findByNameLikeIgnoreCaseOrIngredientsLikeIgnoreCase("%"+name+"%","%"+name+"%");
		if (medicines.isEmpty()) {
			throw new NoMedicinesFoundException("Medicines Not Found");
		}
		return medicines.stream().map(medicineMapper::medicneToMedicineResponse).toList();
	}

	public Medicine validatedMedicines(Row row) {
		Medicine medicine = new Medicine();
		try {
			medicine.setName(row.getCell(0).getStringCellValue());
			medicine.setCategory(row.getCell(1).getStringCellValue());
			medicine.setDosageInMg((int) row.getCell(2).getNumericCellValue());
			medicine.setForm(Form.valueOf((row.getCell(3).getStringCellValue().toUpperCase())));
			medicine.setIngredients(row.getCell(4).getStringCellValue());
			medicine.setManufacturer(row.getCell(5).getStringCellValue());
			medicine.setPrice(row.getCell(6).getNumericCellValue());
			medicine.setExpiryDate(LocalDate.parse(row.getCell(7).getStringCellValue()));
			medicine.setStockQuantity(ThreadLocalRandom.current().nextInt(50, 101));

		} catch (NullPointerException | IllegalStateException | DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid data in row " + row.getRowNum(), e);
		}

		return medicine;

	}

	public void saveMedicine(@Valid Medicine medicine) {
		medicineRepository.save(medicine);

	}

}
