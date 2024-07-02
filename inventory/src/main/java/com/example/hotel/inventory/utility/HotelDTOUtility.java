package com.example.hotel.inventory.utility;

import com.example.hotel.inventory.dto.HotelDTO;
import com.example.hotel.inventory.entity.Hotel;

public class HotelDTOUtility {
	
	public static Hotel convertHotelDtoToDao(HotelDTO dto) {
		Hotel hotelDao = new Hotel();
		hotelDao.setEmailId(dto.getEmailId());
		if (dto.getId()!=null) {
			hotelDao.setId(dto.getId());
		}
		hotelDao.setLocation(dto.getLocation());
		hotelDao.setPhoneNo(dto.getPhoneNo());
		hotelDao.setStatus(dto.getStatus().name());
		hotelDao.setName(dto.getName());
		return hotelDao;
	}
	
	public static HotelDTO convertHotelDaoToDto(Hotel entity) {
		HotelDTO dto = new HotelDTO();
		dto.setId(entity.getId());
		dto.setEmailId(entity.getEmailId());
		dto.setLocation(entity.getLocation());
		dto.setPhoneNo(entity.getPhoneNo());
		if(entity.getStatus().equals(HotelStatus.ACTIVE.name()))
			dto.setStatus(HotelStatus.ACTIVE);
		else 
			dto.setStatus(HotelStatus.CLOSED);
		dto.setName(entity.getName());
		return dto;
	}

}
