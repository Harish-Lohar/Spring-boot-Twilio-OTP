package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.dto.HospitalDto;
import com.hospital.model.Patients;
import com.hospital.service.HospitalService;

@RestController
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	// Insert Patient Data
	@PostMapping("/save")
	public ResponseEntity<String> savePatient(@RequestBody HospitalDto hospitalDto) {
		return hospitalService.savePatient(hospitalDto);
	}

	// Get all Patient Data
	@GetMapping("/patients")
	public List<Patients> allPatients() {
		return hospitalService.getData();
	}

	// Update Patient details
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updatePatient(@PathVariable("id") Long id, @RequestBody HospitalDto hospitalDto) {
		return hospitalService.updatePatient(id, hospitalDto);
	}

	// Delete Patient detail
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePatient(@PathVariable("id") Long id) {
		return hospitalService.deletePatient(id);
	}
	
	@GetMapping("/otp")
	public ResponseEntity<String> sendOTP(){
		
		return hospitalService.sendOtp();
	}

}
