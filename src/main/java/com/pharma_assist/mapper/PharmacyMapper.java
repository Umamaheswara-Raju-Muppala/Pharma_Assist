package com.pharma_assist.mapper;

import org.springframework.stereotype.Component;

import com.pharma_assist.entity.Pharmacy;
import com.pharma_assist.requests.PharmacyRequest;
import com.pharma_assist.responses.PharmacyResponse;
@Component
public class PharmacyMapper {

	public Pharmacy pharmacyRequestToPharmacy(PharmacyRequest pharmacyRequest, Pharmacy pharmacy) {
		pharmacy.setName(pharmacyRequest.getName());
		pharmacy.setGstNo(pharmacyRequest.getGstNo());
		pharmacy.setLicenceNo(pharmacyRequest.getLicenceNo());
		return pharmacy;
	}

	public PharmacyResponse pharmacyToPharmacyResponse(Pharmacy pharmacy) {
		return new PharmacyResponse(pharmacy.getPharmacyId(), pharmacy.getName(), pharmacy.getGstNo(),
				pharmacy.getLicenceNo());
	}

}
