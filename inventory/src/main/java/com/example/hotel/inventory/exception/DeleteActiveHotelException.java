package com.example.hotel.inventory.exception;

@SuppressWarnings("serial")
public class DeleteActiveHotelException extends Exception {
	
	String message;

	public DeleteActiveHotelException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
