package com.hospital.serviceImpl;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hospital.dao.HospitalRepository;
import com.hospital.dto.HospitalDto;
import com.hospital.model.Patients;
import com.hospital.service.HospitalService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class ServiceImplementation implements HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Value("${fromPhoneNo}")
	private String from;

	@Value("${toPhoneNo}")
	private String to;

	@Value("${accountSID}")
	private String ACCOUNT_SID;

	@Value("${authToken}")
	private String AUTH_TOKEN;

	@Override
	public ResponseEntity<String> savePatient(HospitalDto hospitalDto) {
		Patients patients = new Patients();

		// Check Id is Present
		Optional<Patients> Id = hospitalRepository.findById(hospitalDto.getId());
		if (!Id.isPresent()) {
			patients.setId(hospitalDto.getId());
		} else {
			return new ResponseEntity<>("Id Not Available...", HttpStatus.OK);
		}

		// Check Fullname available
		Optional<Patients> fullname = hospitalRepository.findByFullname(hospitalDto.getFullname());

		if (!fullname.isPresent()) {
			patients.setFullname(hospitalDto.getFullname());
		} else {
			return new ResponseEntity<>("Fullname Already Available...", HttpStatus.OK);
		}

		// Check Contact Available
		Optional<Patients> contact = hospitalRepository.findByContact(hospitalDto.getContact());
		if (!contact.isPresent()) {
			patients.setContact(hospitalDto.getContact());
			patients.setAddress(hospitalDto.getAddress());
			patients.setAge(hospitalDto.getAge());
			patients.setGender(hospitalDto.getGender());
			patients.setReason(hospitalDto.getReason());
			hospitalRepository.save(patients);
			return new ResponseEntity<>("Patient Registered Successfully...", HttpStatus.OK);
		} else {

			return new ResponseEntity<>("Contact Already Registered...", HttpStatus.OK);
		}
	}

	@Override
	public List<Patients> getData() {
		List<Patients> list = hospitalRepository.findAll();
		return list;
	}

	@Override
	public ResponseEntity<String> updatePatient(Long id, HospitalDto crudDto) {
		ResponseEntity<String> msg = new ResponseEntity<>("", HttpStatus.OK);
		Optional<Patients> value = hospitalRepository.findById(id);
		System.out.println(value.isPresent());
		if (value.isPresent()) {
			System.out.println(id);
			Patients patients = hospitalRepository.getById(id);
			patients.setFullname(crudDto.getFullname());
			patients.setContact(crudDto.getContact());
			hospitalRepository.save(patients);
			msg = new ResponseEntity<>("Patient Data Updated Successfully... ", HttpStatus.OK);
		} else {
			msg = new ResponseEntity<>("Patient Not Exist...", HttpStatus.OK);
		}
		return msg;
	}

	@Override
	public ResponseEntity<String> deletePatient(Long id) {
		Optional<Patients> optional = hospitalRepository.findById(id);
		if (optional.isPresent()) {
			hospitalRepository.deleteById(id);
			return new ResponseEntity<>("Patient Data Deleted Successfully... ", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Patient Not Exist... ", HttpStatus.OK);
		}

	}

	@Override
	public ResponseEntity<String> sendOtp() {
		int max = 10000000;
		int min = 20000000;
		Long a = (long) (Math.random() * (max - min + 1) + min);
		String msg = "Your OTP is " + a;
		try {
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(from), msg) // to:to which no you
																								// want to send sms
					.setMediaUrl(Arrays.asList(URI.create("https://demo.twilio.com/owl.png"))) // from: twillio given
																								// phone no
					.setStatusCallback(URI.create("http://postb.in/1234abcd")) // body : text message
					.create();
			System.out.println(message);
			System.out.println(message.getSid());
			return new ResponseEntity<>("OTP Send Successfully...", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("OTP Send Failed...", HttpStatus.OK);
	}

}
