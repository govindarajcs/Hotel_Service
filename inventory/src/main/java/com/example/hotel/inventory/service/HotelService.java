package com.example.hotel.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hotel.inventory.dao.HotelDAO;
import com.example.hotel.inventory.dto.HotelDTO;
import com.example.hotel.inventory.dto.RoomDTO;
import com.example.hotel.inventory.entity.Hotel;
import com.example.hotel.inventory.entity.RoomInventory;
import com.example.hotel.inventory.exception.DeleteActiveHotelException;
import com.example.hotel.inventory.exception.DuplicateRecordException;
import com.example.hotel.inventory.exception.RecordNotExistException;
import com.example.hotel.inventory.response.HotelResponseDTO;
import com.example.hotel.inventory.response.RoomResponseDTO;
import com.example.hotel.inventory.utility.HotelDTOUtility;
import com.example.hotel.inventory.utility.HotelStatus;
import com.example.hotel.inventory.utility.RoomDTOUtility;

@Service
public class HotelService {

	@Autowired
	HotelDAO dao;
	
	Logger logger = LoggerFactory.getLogger(HotelService.class);

	@SuppressWarnings("finally")
	public ResponseEntity<HotelResponseDTO> addHotel(HotelDTO dto) {
		HotelResponseDTO hotelResponseDto = null;
		HttpStatus status = null;
		MDC.put("correlationId", "XXXXXXXX");
		logger.info("Inserting Hotel Record "+dto.getName()+" to the application ");
		try {
			if(dto.getStatus()==null) {
				dto.setStatus(HotelStatus.ACTIVE);
			} 
			if (dto.getId()!=null && dao.existsById(dto.getId())) {
				throw new DuplicateRecordException("Duplicate Record found. Record with hotel Id "+dto.getId()+" exist");
			}

			Hotel hotelDao = dao.getHotelByNameAndLocation(dto.getName(), dto.getLocation());
			if(hotelDao!=null) {
				logger.error("Duplicate record "+hotelDao.getName()+" exist in the database with id "+hotelDao.getId());
				throw new DuplicateRecordException("Duplicate Record found. Record with hotel name "+dto.getName()+" and location "+dto.getLocation()+" exist");
			}
			hotelDao = HotelDTOUtility.convertHotelDtoToDao(dto);
			Hotel responseEntity =  dao.save(hotelDao);
			if(responseEntity != null) {
				hotelResponseDto = new HotelResponseDTO("Hotel record created successfully with unique ID "+responseEntity.getId());
				dto.setId(responseEntity.getId());
				hotelResponseDto.setHotelDTO(dto);
				status = HttpStatus.CREATED;
			} else {
				hotelResponseDto = new HotelResponseDTO("Hotel record creation failed");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch(DuplicateRecordException e) {
			hotelResponseDto = new HotelResponseDTO(e.getMessage());
			status = HttpStatus.CONFLICT;
		}catch(Exception e){
			hotelResponseDto = new HotelResponseDTO("Hotel record creation failed due to "+e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} finally {
			ResponseEntity<HotelResponseDTO> responseDTO = new ResponseEntity<HotelResponseDTO>(hotelResponseDto, status);
			return responseDTO;
		}
	}

	@SuppressWarnings("finally")
	public ResponseEntity<HotelResponseDTO> deleteHotel(Integer hotel_id) {
		HotelResponseDTO responseDTO = null;
		HttpStatus status = null;
		try {
			if(dao.existsById(hotel_id)) {
				Hotel hotel = dao.getReferenceById(hotel_id);
				if(hotel.getStatus().equals(HotelStatus.CLOSED.name())) {
					dao.delete(hotel);
					responseDTO = new HotelResponseDTO("Hotel Record of ID "+hotel_id+" removed successfully");
					status = HttpStatus.NO_CONTENT;
				} else {
					throw new DeleteActiveHotelException("Cannot delete hotel "+hotel.getName()+", as the hotel is still active"); 
				}
			} else {
				throw new RecordNotExistException("Hotel with record id "+hotel_id+" doesn't exist");
			}

		} catch(RecordNotExistException e) {
			responseDTO = new HotelResponseDTO(e.getMessage());
			status = HttpStatus.NOT_FOUND;
		}
		catch(DeleteActiveHotelException e) {
			responseDTO = new HotelResponseDTO(e.getMessage());
			status = HttpStatus.FORBIDDEN;
		} catch(Exception e) {
			responseDTO = new HotelResponseDTO("Error in deleting the hotel with ID "+hotel_id+" due to "+e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		finally {
			return new ResponseEntity<HotelResponseDTO>(responseDTO, status);
		}
	}

	@SuppressWarnings("finally")
	public ResponseEntity<HotelResponseDTO> disableHotelRecord(Integer hotelId) {
		HotelResponseDTO responseDTO = null;
		HttpStatus status = null;
		try {
			if(dao.existsById(hotelId)) {
				Hotel hotel = dao.getReferenceById(hotelId);
				if(hotel.getStatus().equals(HotelStatus.ACTIVE.name())) {
					hotel.setStatus(HotelStatus.CLOSED.name());
					dao.save(hotel);
					HotelDTO dto = HotelDTOUtility.convertHotelDaoToDto(hotel);
					responseDTO = new HotelResponseDTO("Hotel Record of ID "+hotelId+" deactivated successfully");
					responseDTO.setHotelDTO(dto);
					status = HttpStatus.CREATED;
				} else {
					throw new DeleteActiveHotelException("Hotel "+hotel.getName()+" in location "+hotel.getLocation()+" is already de-activated"); 
				}
			} else {
				throw new RecordNotExistException("Hotel with record id "+hotelId+" doesn't exist");
			}

		} catch(RecordNotExistException e) {
			responseDTO = new HotelResponseDTO(e.getMessage());
			status = HttpStatus.NOT_FOUND;
		}
		catch(DeleteActiveHotelException e) {
			responseDTO = new HotelResponseDTO(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} catch(Exception e) {
			responseDTO = new HotelResponseDTO("Error in Deactivating the hotel with ID "+hotelId+" due to "+e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		finally {
			return new ResponseEntity<HotelResponseDTO>(responseDTO, status);
		}
	}

	@SuppressWarnings("finally")
	public ResponseEntity<HotelResponseDTO> enableHotelRecord(Integer hotelId) {
		HotelResponseDTO responseDTO = null;
		HttpStatus status = null;
		try {
			if(dao.existsById(hotelId)) {
				Hotel hotel = dao.getReferenceById(hotelId);
				if(hotel.getStatus().equals(HotelStatus.CLOSED.name())) {
					hotel.setStatus(HotelStatus.ACTIVE.name());
					dao.save(hotel);
					HotelDTO dto = HotelDTOUtility.convertHotelDaoToDto(hotel);
					responseDTO = new HotelResponseDTO("Hotel Record of ID "+hotelId+" activated successfully");
					responseDTO.setHotelDTO(dto);
					status = HttpStatus.CREATED;
				} else {
					throw new DeleteActiveHotelException("Hotel "+hotel.getName()+" in location "+hotel.getLocation()+" is already activated"); 
				}
			} else {
				throw new RecordNotExistException("Hotel with record id "+hotelId+" doesn't exist");
			}

		} catch(RecordNotExistException e) {
			responseDTO = new HotelResponseDTO(e.getMessage());
			status = HttpStatus.NOT_FOUND;
		}
		catch(DeleteActiveHotelException e) {
			responseDTO = new HotelResponseDTO(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} catch(Exception e) {
			responseDTO = new HotelResponseDTO("Error in Activating the hotel with ID "+hotelId+" due to "+e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		finally {
			return new ResponseEntity<HotelResponseDTO>(responseDTO, status);
		}
	}

	@SuppressWarnings("finally")
	public ResponseEntity<HotelResponseDTO> updateHotelRecord(HotelDTO dto) {
		HotelResponseDTO responseDTO = null;
		HttpStatus status = null;
		try {
			if(dao.existsById(dto.getId())) {
				Hotel hotel = HotelDTOUtility.convertHotelDtoToDao(dto);
				dao.save(hotel);
				responseDTO = new HotelResponseDTO("Hotel details for the hotel id "+dto.getId()+" updated successfully", dto);
				status = HttpStatus.ACCEPTED;
			} else {
				throw new RecordNotExistException("Hotel with record id "+dto.getId()+" doesn't exist");
			}

		} catch(RecordNotExistException e) {
			responseDTO = new HotelResponseDTO(e.getMessage());
			status = HttpStatus.NOT_FOUND;
		} catch(Exception e) {
			responseDTO = new HotelResponseDTO("Error in updating the details of the hotel with ID "+dto.getId()+" due to "+e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		finally {
			return new ResponseEntity<HotelResponseDTO>(responseDTO, status);
		}
	}
	
	@SuppressWarnings("finally")
	public ResponseEntity<RoomResponseDTO> getAllRoomsOfHotel(Integer hotelId) {
		HttpStatus status = null;
		RoomResponseDTO roomResponseDTO = null;
		List<RoomDTO> roomDTOList = null;
		try {
			if(!dao.existsById(hotelId)) {
				throw new RecordNotExistException("Hotel with record id "+hotelId+" doesn't exist");
			}
			Hotel hotel = dao.getReferenceById(hotelId);
			roomDTOList = new ArrayList<>();
			
			
			for(RoomInventory inventory: hotel.getRoomInventories()) {
				roomDTOList.add(RoomDTOUtility.convertDaoToDto(inventory));
			}
			
			roomResponseDTO = new RoomResponseDTO(roomDTOList);
			status = HttpStatus.OK;
		} catch(RecordNotExistException e) { 
			roomResponseDTO = new RoomResponseDTO(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} catch(Exception e) {
			roomResponseDTO = new RoomResponseDTO("Error in fetching list of rooms for the hotel "+e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} finally {
			ResponseEntity<RoomResponseDTO> responseEntityDTO = new ResponseEntity<RoomResponseDTO>(roomResponseDTO, status);
			return responseEntityDTO;
		}
	}
	
	
	@SuppressWarnings("finally")
	public ResponseEntity<RoomResponseDTO> getAllRoomsOfTypeOfHotel(Integer hotelId, String type) {
		HttpStatus status = null;
		RoomResponseDTO roomResponseDTO = null;
		List<RoomDTO> roomDTOList = null;
		try {
			if(!dao.existsById(hotelId)) {
				throw new RecordNotExistException("Hotel with record id "+hotelId+" doesn't exist");
			}
			Hotel hotel = dao.getReferenceById(hotelId);
			roomDTOList = new ArrayList<>();
			
			List<RoomInventory> filteredList = hotel.getRoomInventories().stream().filter(x->x.getRoomType().equals(type)).collect(Collectors.toList());
			for(RoomInventory inventory: filteredList) {
				roomDTOList.add(RoomDTOUtility.convertDaoToDto(inventory));
			}
			
			roomResponseDTO = new RoomResponseDTO(roomDTOList);
			status = HttpStatus.OK;
		} catch(RecordNotExistException e) { 
			roomResponseDTO = new RoomResponseDTO(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} catch(Exception e) {
			roomResponseDTO = new RoomResponseDTO("Error in fetching list of rooms for the hotel "+e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} finally {
			ResponseEntity<RoomResponseDTO> responseEntityDTO = new ResponseEntity<RoomResponseDTO>(roomResponseDTO, status);
			return responseEntityDTO;
		}
	}
	
	

	@SuppressWarnings("finally")
	public ResponseEntity<HotelResponseDTO> getHotelRecordById(Integer hotelId) {
		HotelResponseDTO responseDTO = null;
		HttpStatus status = null;
		try {
			if(dao.existsById(hotelId)) {
				HotelDTO hotelDTO = HotelDTOUtility.convertHotelDaoToDto(dao.getReferenceById(hotelId));
				responseDTO = new HotelResponseDTO(hotelDTO);
				status = HttpStatus.OK;
			} else {
				throw new RecordNotExistException("Hotel with record id "+hotelId+" doesn't exist");
			}

		} catch(RecordNotExistException e) {
			responseDTO = new HotelResponseDTO(e.getMessage());
			status = HttpStatus.NOT_FOUND;
		} catch(Exception e) {
			responseDTO = new HotelResponseDTO("Error in retrieving the details of the hotel with ID "+hotelId+" due to "+e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		finally {
			return new ResponseEntity<HotelResponseDTO>(responseDTO, status);
		}
	}

	public ResponseEntity<List<HotelDTO>> getAllHotel(){
		ResponseEntity<List<HotelDTO>> responseDTO;
		HttpStatus status;
		List<Hotel> hotelDaoList = dao.findAll();
		List<HotelDTO> hotelDTOList = new ArrayList<HotelDTO>();
		for(Hotel hotel:hotelDaoList) {
			HotelDTO dto = HotelDTOUtility.convertHotelDaoToDto(hotel);
			hotelDTOList.add(dto);
		}
		status = HttpStatus.OK;
		responseDTO = new ResponseEntity<List<HotelDTO>>(hotelDTOList, status);
		return responseDTO;
	}

}
