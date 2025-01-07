package com.pharma_assist.mapper;

import org.springframework.stereotype.Component;

import com.pharma_assist.entity.Bill;
import com.pharma_assist.responses.BillResponse;

@Component
public class BillMapper {

	private final PharmacyMapper pharmacyMapper;
	private final PatientMapper patientMapper;
	private final CartMapper cartMapper;

	public BillMapper(PharmacyMapper pharmacyMapper, PatientMapper patientMapper, CartMapper cartMapper) {
		this.pharmacyMapper = pharmacyMapper;
		this.patientMapper = patientMapper;
		this.cartMapper = cartMapper;
	}

	public BillResponse billToBillResponse(Bill bill) {
		return new BillResponse(bill.getBillId(), bill.getGstInPercentage(), bill.getGrossAmount(),
				bill.getTotalPayableAmount(), bill.getPurchaseTime(), bill.getPayOptions(),
				pharmacyMapper.pharmacyToPharmacyResponse(bill.getPharmacy()),
				patientMapper.patientToPatientResponse(bill.getPatient()),cartMapper.CartToCartResponse(bill.getCart()));
	}

}
