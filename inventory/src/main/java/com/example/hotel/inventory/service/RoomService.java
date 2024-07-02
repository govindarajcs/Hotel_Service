package com.example.hotel.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hotel.inventory.dao.HotelDAO;
import com.example.hotel.inventory.dao.RoomRepository;
import com.example.hotel.inventory.dto.RoomDTO;
import com.example.hotel.inventory.entity.Hotel;
import com.example.hotel.inventory.entity.RoomInventory;
import com.example.hotel.inventory.exception.DuplicateRecordException;
import com.example.hotel.inventory.exception.RecordNotExistException;
import com.example.hotel.inventory.response.RoomResponseDTO;
import com.example.hotel.inventory.utility.RoomDTOUtility;

@Service
public class RoomService {

	@Autowired
	RoomRepository repository;
	
	@Autowired
	HotelDAO hotelRepository;

	@SuppressWarnings("finally")
	public ResponseEntity<RoomResponseDTO> addRoom(Integer hotelId, RoomDTO dto) {
		HttpStatus status = null;
		RoomResponseDTO roomResponseDTO = null;
		try {
			
			if(!hotelRepository.existsById(hotelId)) {
				throw new RecordNotExistException("Hotel "+hotelId+" doesn't exist");
			}
			Hotel hotelEntity = hotelRepository.getReferenceById(hotelId);
			
			if(repository.getByRoomTypeAndHotel(dto.getType().name(), hotelEntity) != null) {
				throw new DuplicateRecordException("Room Type "+dto.getType().name()+" already defined for entity "+hotelEntity.getName());
			}
			RoomInventory inventory = RoomDTOUtility.convertDtoToDao(dto, hotelEntity);
			RoomInventory responeInventory = repository.save(inventory);
			RoomDTO responseDTO = RoomDTOUtility.convertDaoToDto(responeInventory);
			status = HttpStatus.ACCEPTED;
			roomResponseDTO = new RoomResponseDTO("Room Detail "+responseDTO.getId()+" added for the hotel"+hotelId, responseDTO);
		} catch(RecordNotExistException e) {
			roomResponseDTO = new RoomResponseDTO(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} catch(DuplicateRecordException e) { 
			roomResponseDTO = new RoomResponseDTO(e.getMessage());
			status = HttpStatus.CONFLICT;
		} catch(Exception e) {
			roomResponseDTO = new RoomResponseDTO("Error in adding room details due to "+e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} finally {
			ResponseEntity<RoomResponseDTO> responseEntityDTO = new ResponseEntity<RoomResponseDTO>(roomResponseDTO, status);
			return responseEntityDTO;
		}
	}
	
	@SuppressWarnings("finally")
	public ResponseEntity<RoomResponseDTO> deleteRoom(Integer roomId) {
		
		HttpStatus status = null;
		RoomResponseDTO roomResponseDTO = null;
		try {
			RoomInventory inventory = repository.getReferenceById(roomId);
			if(inventory == null) {
				throw new RecordNotExistException("Room Id "+roomId+" doesn't exist");
			}
			repository.delete(inventory);
			roomResponseDTO = new RoomResponseDTO("Room type "+roomId+" removed successfully for the hotel "+inventory.getHotel().getName());
			status = HttpStatus.NO_CONTENT;
		} catch(RecordNotExistException e) { 
			roomResponseDTO = new RoomResponseDTO(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} catch(Exception e) {
			roomResponseDTO = new RoomResponseDTO("Error in deleting room details due to "+e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} finally {
			ResponseEntity<RoomResponseDTO> responseEntityDTO = new ResponseEntity<RoomResponseDTO>(roomResponseDTO, status);
			return responseEntityDTO;
		}
	}
	
	@SuppressWarnings("finally")
	public ResponseEntity<RoomResponseDTO> updateRoomRent(Integer roomId, Integer price) {
		HttpStatus status = null;
		RoomResponseDTO roomResponseDTO = null;
		try {
			RoomInventory inventory = repository.getReferenceById(roomId);
			if(inventory == null) {
				throw new RecordNotExistException("Room Id "+roomId+" doesn't exist");
			}
			inventory.setRent(price);
			repository.save(inventory);
			roomResponseDTO = new RoomResponseDTO("Room rent for the id"+roomId+" updated successfully to "+price+" for the hotel "+inventory.getHotel().getName());
			status = HttpStatus.NO_CONTENT;
		} catch(RecordNotExistException e) { 
			roomResponseDTO = new RoomResponseDTO(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} catch(Exception e) {
			roomResponseDTO = new RoomResponseDTO("Error in updating room rent due to "+e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} finally {
			ResponseEntity<RoomResponseDTO> responseEntityDTO = new ResponseEntity<RoomResponseDTO>(roomResponseDTO, status);
			return responseEntityDTO;
		}
	}
	
	
	@SuppressWarnings("finally")
	public ResponseEntity<RoomResponseDTO> updateTotalRoom(Integer roomId, Integer count) {
		HttpStatus status = null;
		RoomResponseDTO roomResponseDTO = null;
		try {
			RoomInventory inventory = repository.getReferenceById(roomId);
			if(inventory == null) {
				throw new RecordNotExistException("Room Id "+roomId+" doesn't exist");
			}
			inventory.setRoomCount(count);
			repository.save(inventory);
			roomResponseDTO = new RoomResponseDTO("Room count for the id"+roomId+" updated successfully to "+count+" for the hotel "+inventory.getHotel().getName());
			status = HttpStatus.NO_CONTENT;
		} catch(RecordNotExistException e) { 
			roomResponseDTO = new RoomResponseDTO(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} catch(Exception e) {
			roomResponseDTO = new RoomResponseDTO("Error in updaitng room count due to "+e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} finally {
			ResponseEntity<RoomResponseDTO> responseEntityDTO = new ResponseEntity<RoomResponseDTO>(roomResponseDTO, status);
			return responseEntityDTO;
		}
	}
}
