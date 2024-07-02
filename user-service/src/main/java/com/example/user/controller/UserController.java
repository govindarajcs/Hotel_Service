package com.example.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.user.dto.UserDTO;
import com.example.user.dto.UserResponseDTO;
import com.example.user.service.UserService;

@RequestMapping(path = "/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> addUser(UserDTO dto) {
		return service.addUser(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable("id") Integer id) {
		return service.deleteUserDetails(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") Integer id, UserDTO dto) {
		return service.updateUserDetails(dto, id);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUser() {
		return service.getAllUser();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") Integer id){
		return service.getUserDetails(id);
	}

}
