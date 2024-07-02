package com.example.hotel.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the hotel database table.
 * 
 */
@Entity
@NamedQuery(name="Hotel.findAll", query="SELECT h FROM Hotel h")
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="email_id")
	private String emailId;
	
	private String name;

	private String location;

	@Column(name="phone_no")
	private String phoneNo;

	private String status;

	//bi-directional many-to-one association to RoomInventory
	@OneToMany(mappedBy="hotel")
	private List<RoomInventory> roomInventories;

	public Hotel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<RoomInventory> getRoomInventories() {
		return this.roomInventories;
	}

	public void setRoomInventories(List<RoomInventory> roomInventories) {
		this.roomInventories = roomInventories;
	}

	public RoomInventory addRoomInventory(RoomInventory roomInventory) {
		getRoomInventories().add(roomInventory);
		roomInventory.setHotel(this);

		return roomInventory;
	}

	public RoomInventory removeRoomInventory(RoomInventory roomInventory) {
		getRoomInventories().remove(roomInventory);
		roomInventory.setHotel(null);

		return roomInventory;
	}

}