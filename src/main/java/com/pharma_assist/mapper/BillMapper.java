package com.pharma_assist.mapper;

import org.springframework.stereotype.Component;

import com.pharma_assist.entity.Bill;
import com.pharma_assist.responses.BillResponse;

@Component
public class BillMapper {

	private final PharmacyMapper pharmacyMapper;
	private final PatientMapper patientMapper;

	public BillMapper(PharmacyMapper pharmacyMapper, PatientMapper patientMapper) {
		this.pharmacyMapper = pharmacyMapper;
		this.patientMapper = patientMapper;
	}

	public BillResponse billToBillResponse(Bill bill) {
		return new BillResponse(bill.getBillId(), bill.getGstInPercentage(), bill.getGrossAmount(),
				bill.getTotalPayableAmount(), bill.getPurchaceTime(), bill.getPayOptions(),
				pharmacyMapper.pharmacyToPharmacyResponse(bill.getPharmacy()),
				patientMapper.patientToPatientResponse(bill.getPatient()));
	}

}
