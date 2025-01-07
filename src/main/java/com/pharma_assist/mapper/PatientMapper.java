package com.pharma_assist.mapper;

import org.springframework.stereotype.Component;

import com.pharma_assist.entity.Patient;
import com.pharma_assist.requests.PatientRequest;
import com.pharma_assist.responses.PatientResponse;

@Component
public class PatientMapper {

	public Patient patientRequestToPatient(PatientRequest patientRequest, Patient patient) {
		patient.setName(patientRequest.getName());
		patient.setEmail(patientRequest.getEmail());
		patient.setPhoneNumber(patientRequest.getPhoneNumber());
		patient.setGender(patientRequest.getGender());
		patient.setDateOfBirth(patientRequest.getDateOfBirth());
		return patient;
	}

	public PatientResponse patientToPatientResponse(Patient patient) {
		return new PatientResponse(patient.getPatientId(), patient.getName(), patient.getPhoneNumber(),
				patient.getEmail(), patient.getGender(), patient.getDateOfBirth());
	}

}
