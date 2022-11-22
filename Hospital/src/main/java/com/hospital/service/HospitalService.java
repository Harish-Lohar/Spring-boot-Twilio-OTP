package com.hospital.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hospital.dto.HospitalDto;
import com.hospital.model.Patients;

public interface HospitalService {

	ResponseEntity<String> savePatient(HospitalDto hospitalDto);

	List<Patients> getData();

	ResponseEntity<String> updatePatient(Long id, HospitalDto hospitalDto);

	ResponseEntity<String> deletePatient(Long id);

	ResponseEntity<String> sendOtp();

}
