package com.pharma_assist.mapper;


import org.springframework.stereotype.Component;

import com.pharma_assist.entity.Medicine;
import com.pharma_assist.requests.MedicineRequest;
import com.pharma_assist.responses.MedicineResponse;

@Component
public class MedicineMapper {

	public Medicine medicineRequestToMedicine(MedicineRequest medicineRequest, Medicine medicine) {
		medicine.setName(medicineRequest.getName());
		medicine.setCategory(medicineRequest.getCategory());
		medicine.setDosageInMg(medicineRequest.getDosageInMg());
		medicine.setForm(medicineRequest.getForm());
		medicine.setIngredients(medicineRequest.getIngredients());
		medicine.setManufacturer(medicineRequest.getManufacturer());
		medicine.setPrice(medicineRequest.getPrice());
		medicine.setExpiryDate(medicineRequest.getExpiryDate());
		medicine.setStockQuantity(medicineRequest.getStockQuantity());
		return medicine;
	}

	public MedicineResponse medicneToMedicineResponse(Medicine medicine) {
		return new MedicineResponse(medicine.getMedicineId(), medicine.getName(), medicine.getCategory(),
				medicine.getIngredients(), medicine.getDosageInMg(), medicine.getForm(), medicine.getManufacturer(),
				medicine.getStockQuantity(), medicine.getExpiryDate(), medicine.getPrice());

	}

}
