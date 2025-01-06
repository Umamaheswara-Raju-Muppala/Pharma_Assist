package com.pharma_assist.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pharma_assist.entity.Bill;
import com.pharma_assist.entity.Cart;
import com.pharma_assist.entity.Item;
import com.pharma_assist.entity.Patient;
import com.pharma_assist.entity.Pharmacy;
import com.pharma_assist.exceptions.BillNotFoundException;
import com.pharma_assist.exceptions.CartNotFoundException;
import com.pharma_assist.exceptions.PatientNotFoundException;
import com.pharma_assist.exceptions.PharmacyNotFoundException;
import com.pharma_assist.mapper.BillMapper;
import com.pharma_assist.repository.BillRepository;
import com.pharma_assist.repository.CartRepository;
import com.pharma_assist.repository.PatientRepository;
import com.pharma_assist.responses.BillResponse;
import com.pharma_assist.utiliy.SimpleResponseStructure;

@Service
public class BillService {
	private final BillRepository billRepository;
	private final CartRepository cartRepository;
	private final PatientRepository patientRepository;
	private final BillMapper billMapper;

	public BillService(BillRepository billRepository, CartRepository cartRepository,
			PatientRepository patientRepository, BillMapper billMapper) {
		this.billRepository = billRepository;
		this.cartRepository = cartRepository;
		this.patientRepository = patientRepository;
		this.billMapper = billMapper;
	}

	public String createBill(String cartId, String phoneNumber) {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new CartNotFoundException("Cart Not Found or Invalid Cart ID " + cartId));
		Patient patient = patientRepository.findByPhoneNumber(phoneNumber)
				.orElseThrow(() -> new PatientNotFoundException(
						"Patient Not Found/Invalid Patient Phone Number,Provided Phone Number is " + phoneNumber));
		Pharmacy pharmacy = Optional.ofNullable(patient.getPharmacy()).orElseThrow(() -> new PharmacyNotFoundException(
				"Pharmacy not found for this patient. Please associate the patient with a pharmacy before performing this action."));

		Bill bill = new Bill();
		bill.setGrossAmount(cart.getItems().stream().mapToDouble(Item::getTotalPrice).sum());
		bill.setGstInPercentage(18.0);
		bill.setPurchaceTime(LocalDateTime.now());
		bill.setTotalPayableAmount(bill.getGrossAmount() + (bill.getGstInPercentage() / 100 * bill.getGrossAmount()));
		bill.setCart(cart);
		bill.setPatient(patient);
		bill.setPharmacy(pharmacy);
		billRepository.save(bill);
		return "BillId:" + bill.getBillId();
	}

	public BillResponse getBill(String billId) {

		return billRepository.findById(billId).map(billMapper::billToBillResponse).orElseThrow(
				() -> new BillNotFoundException("Invalid Bill Id/Bill Not Found .Try again!! using avalid Bill Id"));
	}

	public String deleteBill(String billId) {
		Bill bill = billRepository.findById(billId)
				.orElseThrow(() -> new BillNotFoundException("Bill Not Found/Invalid Bill Id:" + billId));

		Pharmacy pharmacy = bill.getPharmacy();
		if (pharmacy != null) {
			pharmacy.getBills().remove(bill);
			bill.setPharmacy(null);
		}
		Patient patient = bill.getPatient();
		if (patient != null) {
			patient.getBills().remove(bill);
			bill.setPatient(null);
		}
		bill.setCart(null);

		billRepository.delete(bill);
		return "Bill Found and Deleted having bill Id:" + billId;

	}

}
