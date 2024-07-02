package com.example.hotel.inventory.dto;

import com.example.hotel.inventory.utility.HotelStatus;

public class HotelDTO {
	
	Integer id;
	String name;
	String emailId;
	String phoneNo;
	String location;
	HotelStatus status;
	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public HotelStatus getStatus() {
		return status;
	}
	public void setStatus(HotelStatus status) {
		this.status = status;
	}
	public HotelDTO(String name, String emailId, String phoneNo, String location) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.location = location;
	}
	public HotelDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
