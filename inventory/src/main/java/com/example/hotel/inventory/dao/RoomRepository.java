package com.example.hotel.inventory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.inventory.entity.Hotel;
import com.example.hotel.inventory.entity.RoomInventory;

public interface RoomRepository extends JpaRepository<RoomInventory, Integer> {
	
	public RoomInventory getByRoomTypeAndHotel(String roomType, Hotel hotel);
	
}
