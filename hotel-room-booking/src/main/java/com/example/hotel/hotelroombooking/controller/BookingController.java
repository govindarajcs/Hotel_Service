package com.example.hotel.hotelroombooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.hotelroombooking.dto.HotelDTO;
import com.example.hotel.hotelroombooking.service.HotelBookingService;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingController {
	
	@Autowired
	HotelBookingService service;
	
	@GetMapping
	public List<HotelDTO> getAllHotel() {
		return service.getAllHotels();
	}

}
