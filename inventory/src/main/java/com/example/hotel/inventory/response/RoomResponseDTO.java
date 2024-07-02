package com.example.hotel.inventory.response;

import java.util.List;

import com.example.hotel.inventory.dto.RoomDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RoomResponseDTO {

	String message;
	RoomDTO dto;
	
	List<RoomDTO> roomDTOList;
	
	public RoomResponseDTO(List<RoomDTO> roomDTOList) {
		super();
		this.roomDTOList = roomDTOList;
	}
	public RoomResponseDTO(String message) {
		super();
		this.message = message;
	}
	public RoomResponseDTO(String message, RoomDTO dto) {
		super();
		this.message = message;
		this.dto = dto;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public RoomDTO getDto() {
		return dto;
	}
	public void setDto(RoomDTO dto) {
		this.dto = dto;
	}
	public List<RoomDTO> getRoomDTOList() {
		return roomDTOList;
	}
	public void setRoomDTOList(List<RoomDTO> roomDTOList) {
		this.roomDTOList = roomDTOList;
	}

}
