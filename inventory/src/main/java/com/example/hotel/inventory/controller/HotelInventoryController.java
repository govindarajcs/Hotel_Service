package com.example.hotel.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.inventory.dto.HotelDTO;
import com.example.hotel.inventory.dto.RoomDTO;
import com.example.hotel.inventory.response.HotelResponseDTO;
import com.example.hotel.inventory.response.RoomResponseDTO;
import com.example.hotel.inventory.service.HotelService;
import com.example.hotel.inventory.service.RoomService;

@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class HotelInventoryController {
	
	@Autowired
	HotelService service;
	
	@Autowired
	RoomService roomService;
	
	@PostMapping("/v1")
	public ResponseEntity<HotelResponseDTO> addHotel(@RequestBody HotelDTO hotelDTO) {
		return service.addHotel(hotelDTO);
	}
	
	@DeleteMapping("/v1/{id}")
	public ResponseEntity<HotelResponseDTO> deleteHotel(@PathVariable("id") Integer hotel_id) {
		return service.deleteHotel(hotel_id);
	}
	
	@PutMapping("/v1/{id}/activate")
	public ResponseEntity<HotelResponseDTO> activateHotel(@PathVariable("id") Integer hotel_id) {
		return service.enableHotelRecord(hotel_id);
	}
	
	@PutMapping("/v1/{id}/deactivate")
	public ResponseEntity<HotelResponseDTO> deActivateHotel(@PathVariable("id") Integer hotel_id) {
		return service.disableHotelRecord(hotel_id);
	}
	
	@PutMapping("/v1")
	public ResponseEntity<HotelResponseDTO> updateHotel(@RequestBody HotelDTO hotelDTO) {
		return service.updateHotelRecord(hotelDTO);
	} 
	
	@GetMapping("/v1/{id}")
	public ResponseEntity<HotelResponseDTO> getHotelById(@PathVariable("id") Integer hotel_id) {
		return service.getHotelRecordById(hotel_id);
	}
	
	@GetMapping("/v1")
	public ResponseEntity<List<HotelDTO>> getAllHotels() {
		return service.getAllHotel();
	}
	
	@PostMapping("/v1/{id}/room")
	public ResponseEntity<RoomResponseDTO> addRoomDetails(@PathVariable("id") Integer hotelId, @RequestBody RoomDTO dto) {
		return roomService.addRoom(hotelId, dto);
	}
	
	@DeleteMapping("/v1/{hotel_id}/room/{id}")
	public ResponseEntity<RoomResponseDTO> deleteRoom(@PathVariable("hotel_id") Integer hotel_id, @PathVariable("id") Integer id) {
		return roomService.deleteRoom(id);
	}
	
	@PutMapping("/v1/{hotel_id}/room/{id}/rent")
	public ResponseEntity<RoomResponseDTO> updateRent(@PathVariable("hotel_id") Integer hotel_id, @PathVariable("id") Integer id, @RequestBody RoomDTO dto) {
		return roomService.updateRoomRent(id, dto.getRoomRent());
	}
	
	@PutMapping("/v1/{hotel_id}/room/{id}/count")
	public ResponseEntity<RoomResponseDTO> updateCount(@PathVariable("hotel_id") Integer hotel_id, @PathVariable("id") Integer id, @RequestBody RoomDTO dto) {
		return roomService.updateTotalRoom(id, dto.getRoomCount());
	}
	
	@GetMapping("/v1/{hotel_id}/room")
	public ResponseEntity<RoomResponseDTO> getAllRoomsOfHotel(@PathVariable("hotel_id") Integer hotel_id, @RequestParam(required = false,name = "roomType") String roomType) {
		if(roomType ==null)
			return service.getAllRoomsOfHotel(hotel_id);
		else
			return service.getAllRoomsOfTypeOfHotel(hotel_id, roomType);
	}
	
}
