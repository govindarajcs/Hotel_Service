package com.example.hotel.inventory.dto;

import com.example.hotel.inventory.utility.RoomType;

public class RoomDTO {
	
	Integer id;
	RoomType type;
	Integer roomRent;
	Integer hotelId;
	Integer roomCount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public RoomType getType() {
		return type;
	}
	public void setType(RoomType type) {
		this.type = type;
	}
	public Integer getRoomRent() {
		return roomRent;
	}
	public void setRoomRent(Integer roomRent) {
		this.roomRent = roomRent;
	}
	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	public Integer getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}
	
	
	
}
