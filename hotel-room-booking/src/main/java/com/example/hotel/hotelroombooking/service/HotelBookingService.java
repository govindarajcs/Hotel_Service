package com.example.hotel.hotelroombooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel.hotelroombooking.dto.HotelDTO;
import com.example.hotel.hotelroombooking.inventory.hotel.HotelInventory;

@Service
public class HotelBookingService {
	
	@Autowired
	HotelInventory inventory;
	
	public List<HotelDTO> getAllHotels() {
		return inventory.getAllHotels();
	}
	

}
