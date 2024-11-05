package com.pharma_assist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pharma_assist.entity.Patient;
import com.pharma_assist.entity.Pharmacy;
import com.pharma_assist.exceptions.NoPatientsFoundException;
import com.pharma_assist.exceptions.PatientNotFoundException;
import com.pharma_assist.exceptions.PharmacyNotFoundException;
import com.pharma_assist.mapper.PatientMapper;
import com.pharma_assist.repository.PatientRepository;
import com.pharma_assist.repository.PharmacyRepository;
import com.pharma_assist.requests.PatientRequest;
import com.pharma_assist.responses.PatientResponse;

@Service
public class PatientService {
	private final PatientRepository patientRepository;
	private final PatientMapper patientMapper;
	private final PharmacyRepository pharmacyRepository;

	public PatientService(PatientRepository patientRepository, PatientMapper patientMapper,
			PharmacyRepository pharmacyRepository) {
		super();
		this.patientRepository = patientRepository;
		this.patientMapper = patientMapper;
		this.pharmacyRepository = pharmacyRepository;
	}

	public List<PatientResponse> findAllPatientsByPharmacyId(String pharmacyId) {
		List<Patient> patients = pharmacyRepository.findById(pharmacyId).map(Pharmacy::getPatient)
				.orElseThrow(() -> new PharmacyNotFoundException("Pharmacy Not Found"));
		if (patients.isEmpty()) {
			throw new NoPatientsFoundException("Patients Not Found");
		}
		return patients.stream().map(patientMapper::patientToPatientResponse).toList();

	}

	public PatientResponse addPatient(PatientRequest patientRequest, String pharmacyId) {
		Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
				.orElseThrow(() -> new PharmacyNotFoundException("Pharmacy Not Found"));
		Patient patient = patientMapper.patientRequestToPatient(patientRequest, new Patient());
		patient.setPharmacy(pharmacy);
		pharmacy.getPatient().add(patient);
		pharmacyRepository.save(pharmacy);
		return patientMapper.patientToPatientResponse(patientRepository.save(patient));

	}
}
