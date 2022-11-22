package com.hospital.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.model.Patients;

public interface HospitalRepository extends JpaRepository<Patients, Long> {

	Optional<Patients> findByContact(Long contact);

	Optional<Patients> findByFullname(String fullname);

	

}
