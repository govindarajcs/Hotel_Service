package com.example.hotel.inventory.exception;

@SuppressWarnings("serial")
public class RecordNotExistException extends Exception {
	
	String message;

	public RecordNotExistException(String message) {
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
