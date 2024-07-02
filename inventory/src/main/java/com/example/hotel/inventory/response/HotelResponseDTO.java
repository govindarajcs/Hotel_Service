package com.example.hotel.inventory.response;

import com.example.hotel.inventory.dto.HotelDTO;

public class HotelResponseDTO {
	
	String message;
	HotelDTO hotelDTO;
	
	public HotelResponseDTO(HotelDTO hotelDTO) {
		super();
		this.hotelDTO = hotelDTO;
	}
	public HotelResponseDTO(String message) {
		super();
		this.message = message;
	}
	public HotelResponseDTO(String message, HotelDTO hotelDTO) {
		super();
		this.message = message;
		this.hotelDTO = hotelDTO;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HotelDTO getHotelDTO() {
		return hotelDTO;
	}
	public void setHotelDTO(HotelDTO hotelDTO) {
		this.hotelDTO = hotelDTO;
	}
	
	
	
	
}
