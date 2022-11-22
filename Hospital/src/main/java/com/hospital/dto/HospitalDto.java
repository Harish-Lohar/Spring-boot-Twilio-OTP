package com.hospital.dto;

public class HospitalDto {

	private Long id;
	private String fullname;
	private int age;
	private String gender;
	private Long contact;
	private String address;
	private String reason;
	
	public Long getId() {
		return id;
	}
	public String getFullname() {
		return fullname;
	}
	public int getAge() {
		return age;
	}
	public String getGender() {
		return gender;
	}
	public Long getContact() {
		return contact;
	}
	public String getAddress() {
		return address;
	}
	public String getReason() {
		return reason;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setContact(Long contact) {
		this.contact = contact;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

}
