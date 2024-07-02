package com.example.hotel.inventory.utility;

import java.util.HashMap;
import java.util.Map;

import com.example.hotel.inventory.dto.RoomDTO;
import com.example.hotel.inventory.entity.Hotel;
import com.example.hotel.inventory.entity.RoomInventory;

public class RoomDTOUtility {
	
	static Map<String, RoomType> mapRoomType = new HashMap<>();
	static {
		for (RoomType type: RoomType.values()) {
			mapRoomType.put(type.name(), type);
		}
	}
	
	static public RoomDTO convertDaoToDto(RoomInventory roomDao) {
		RoomDTO dto = new RoomDTO();
		dto.setId(roomDao.getId());
		dto.setHotelId(roomDao.getHotel().getId());
		dto.setRoomCount(roomDao.getRoomCount());
		dto.setRoomRent(roomDao.getRent());
		dto.setType(mapRoomType.get(roomDao.getRoomType()));
		return dto;
	}
	
	
	static public RoomInventory convertDtoToDao(RoomDTO dto, Hotel hotel) {
		
		RoomInventory dao = new RoomInventory();
		dao.setRent(dto.getRoomRent());
		dao.setRoomCount(dto.getRoomCount());
		dao.setRoomType(dto.getType().name());
		dao.setHotel(hotel);
		//dao.getHotel().getRoomInventories().add(dao);
		return dao;
	}
	

}
