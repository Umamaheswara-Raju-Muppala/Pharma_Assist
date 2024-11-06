package com.pharma_assist.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.pharma_assist.enums.Form;
import com.pharma_assist.exceptions.InvalidFormException;
import com.pharma_assist.exceptions.NoMedicinesFoundException;
import com.pharma_assist.exceptions.PharmacyNotFoundException;
import com.pharma_assist.entity.Medicine;
import com.pharma_assist.entity.Pharmacy;
import com.pharma_assist.mapper.MedicineMapper;
import com.pharma_assist.repository.MedicineRepository;
import com.pharma_assist.repository.PharmacyRepository;
import com.pharma_assist.requests.MedicineRequest;
import com.pharma_assist.responses.MedicineResponse;

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
		List<Medicine> medicines = new ArrayList<Medicine>();
		Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
				.orElseThrow(() -> new PharmacyNotFoundException("Pharmacy NOt Found"));

		try (XSSFWorkbook workBook = new XSSFWorkbook(file.getInputStream())) {
			for (Sheet sheet : workBook) {
				for (Row row : sheet) {
					if (row.getRowNum() != 0) {
						MedicineRequest request = new MedicineRequest();
						request.setName(row.getCell(0).getStringCellValue());
						request.setCategory(row.getCell(1).getStringCellValue());
						request.setDosageInMg((int) row.getCell(2).getNumericCellValue());
						try {
							request.setForm(Form.valueOf((row.getCell(3).getStringCellValue().toUpperCase())));
						} catch (IllegalArgumentException exception) {
							throw new InvalidFormException("Invalid Form value Input at Row " + row.getRowNum()
									+ " discareded data " + row.getCell(3).getStringCellValue());
						}
						request.setIngredients(row.getCell(4).getStringCellValue());
						request.setManufacturer(row.getCell(5).getStringCellValue());
						request.setPrice(row.getCell(6).getNumericCellValue());
						request.setExpiryDate(LocalDate.parse(row.getCell(7).getStringCellValue()));
						request.setStockQuantity(ThreadLocalRandom.current().nextInt(50, 101));
						Medicine medicine = medicineMapper.medicineRequestToMedicine(request, new Medicine());
						medicine.setPharmacy(pharmacy);
						medicines.add(medicine);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		pharmacy.getMedicine().addAll(medicines);
		pharmacyRepository.save(pharmacy);
		medicineRepository.saveAll(medicines);
		return "Medicines added Suucessfully";
	}

	public List<MedicineResponse> findMedicineByNameOrDosage(String name, int dosageInMg) {
		List<Medicine> medicines = medicineRepository.findMedicineByNameOrDosage(name, dosageInMg);
		if (medicines.isEmpty()) {
			throw new NoMedicinesFoundException("Medicines Not Found");
		}
		return medicines.stream().map(medicineMapper::medicneToMedicineResponse).toList();
	}

}
