package com.example.hotel.inventory.exception;

@SuppressWarnings("serial")
public class DuplicateRecordException extends Exception {
	
	String message;
	
	public DuplicateRecordException(String message) {
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
