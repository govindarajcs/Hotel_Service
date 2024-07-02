package com.example.hotel.inventory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.inventory.entity.Hotel;

public interface HotelDAO extends JpaRepository<Hotel, Integer> {
	
	public Hotel getHotelByNameAndLocation(String name, String location);
	

}
