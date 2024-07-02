package com.example.hotel.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the room_inventory database table.
 * 
 */
@Entity
@Table(name="room_inventory")
@NamedQuery(name="RoomInventory.findAll", query="SELECT r FROM RoomInventory r")
public class RoomInventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int rent;

	@Column(name="room_count")
	private int roomCount;

	@Column(name="room_type")
	private String roomType;

	//bi-directional many-to-one association to Hotel
	@ManyToOne
	private Hotel hotel;

	public RoomInventory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRent() {
		return this.rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public int getRoomCount() {
		return this.roomCount;
	}

	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}

	public String getRoomType() {
		return this.roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}