package com.example.user.exception;

public class UserException extends Exception {

	private static final long serialVersionUID = 1L;
	String message;
	
	public UserException(String message) {
		super();
		this.message = message;
	}
	public UserException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public UserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public UserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
