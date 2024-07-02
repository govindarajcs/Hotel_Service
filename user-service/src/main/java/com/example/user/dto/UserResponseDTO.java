package com.example.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDTO {
	
	String message;
	UserDTO userDto;

	public UserResponseDTO(String message, UserDTO userDto) {
		super();
		this.message = message;
		this.userDto = userDto;
	}
	
	public UserResponseDTO(UserDTO userDto) {
		super();
		this.userDto = userDto;
	}

	public UserResponseDTO(String message) {
		super();
		this.message = message;
	}
	

}
