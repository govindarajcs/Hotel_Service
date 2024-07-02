package com.example.hotel.hotelroombooking.inventory.hotel;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.hotel.hotelroombooking.dto.HotelDTO;

@FeignClient(name="inventory-service", url="${feign.hotel.inventory.url}", path = "/hotel")
public interface HotelInventory {
	
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/v1/")
	public List<HotelDTO> getAllHotels();
	
}
