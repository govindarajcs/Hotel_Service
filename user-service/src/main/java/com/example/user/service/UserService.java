package com.example.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.user.dao.UserRepository;
import com.example.user.dto.UserDTO;
import com.example.user.dto.UserResponseDTO;
import com.example.user.entity.UserDetail;
import com.example.user.entity.UserHistory;
import com.example.user.exception.UserException;

@Service
public class UserService {
	
	@Autowired
	UserDetail detail;
	
	@Autowired
	UserHistory history;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	UserRepository repository;
	
	public ResponseEntity<UserResponseDTO> addUser(UserDTO dto) {
		UserResponseDTO response;
		String message;
		HttpStatus status;
		try {
			if(repository.findByEmailId(dto.getEmailId())!=null) {
				throw new UserException("User with the email Id "+dto.getEmailId()+" exist");
			}
			if(repository.findByPhoneNo(dto.getPhoneNo())!=null) {
				throw new UserException("User with the phone No "+dto.getPhoneNo()+" already exist");
			}
			detail = mapper.map(dto, UserDetail.class);
			history.setCreateTimestamp(new Date());
			history.setUserDetail(detail);
			detail = repository.save(detail);
			dto = mapper.map(detail, UserDTO.class);
			message = "User record added successfully with user_id "+dto.getEmailId();
			status = HttpStatus.ACCEPTED;
		} catch(UserException e) {
			message = e.getLocalizedMessage();
			status = HttpStatus.BAD_REQUEST;
		}catch(Exception e) {
			message = "User Record creation failed due to "+e.getLocalizedMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response = new UserResponseDTO(message, dto);
		return new ResponseEntity<UserResponseDTO>(response, status);
	}
	
	public ResponseEntity<UserResponseDTO> updateUserDetails(UserDTO dto, Integer id) {
		UserResponseDTO response;
		String message;
		HttpStatus status;
		try {
			Optional<UserDetail> optionUserDetail = repository.findById(id);
			if(optionUserDetail.isEmpty()) {
				throw new UserException("User with the id "+id+ "does not exist");
			}
			UserDetail user = optionUserDetail.get();
			user = mapper.map(dto, UserDetail.class);
			repository.save(user);
			status = HttpStatus.ACCEPTED;
			message = "User record "+user.getEmailId()+" updated successfully";
		} catch(UserException e) {
			message = e.getLocalizedMessage();
			status = HttpStatus.BAD_REQUEST;
		} catch(Exception e) {
			message = "User Record updation failed due to "+e.getLocalizedMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response = new UserResponseDTO(message, dto);
		return new ResponseEntity<UserResponseDTO>(response, status);
	}
	
	public ResponseEntity<UserResponseDTO> deleteUserDetails(Integer id) {
		UserResponseDTO response;
		String message;
		HttpStatus status;
		try {
			Optional<UserDetail> optionalUserDetail = repository.findById(id);
			if(optionalUserDetail.isEmpty()) {
				throw new UserException("User with id "+id+" doesnot exist");
			}
			UserDetail userDetail = optionalUserDetail.get();
			repository.delete(userDetail);
			message = "User with id "+id+" deleted successfully";
			status = HttpStatus.NO_CONTENT;
		} catch(UserException e) {
			message = e.getLocalizedMessage();
			status = HttpStatus.BAD_REQUEST;
		} catch(Exception e) {
			message = "User Record deletion failed due to "+e.getLocalizedMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response = new UserResponseDTO(message);
		return new ResponseEntity<UserResponseDTO>(response, status);
	}
	
	public ResponseEntity<List<UserDTO>> getAllUser() {
		List<UserDetail> userDetailList = repository.findAll();
		List<UserDTO> userDTOList = new ArrayList<>();
		for(UserDetail userDetail: userDetailList) {
			UserDTO dto = mapper.map(userDetail, UserDTO.class);
			userDTOList.add(dto);
		}
		return new ResponseEntity<List<UserDTO>>(userDTOList, HttpStatus.OK);
	}
	
	public ResponseEntity<UserResponseDTO> getUserDetails(Integer id) {
		HttpStatus status;
		String message = null;
		UserResponseDTO response;
		UserDTO dto = null;
		try {
			Optional<UserDetail> optionalUserDetail = repository.findById(id);
			if(optionalUserDetail.isEmpty()) {
				throw new UserException("User with id "+id+" doesn't exist");
			}
			UserDetail detail = optionalUserDetail.get();
			status = HttpStatus.OK;
			dto = mapper.map(detail, UserDTO.class);
		} catch(UserException e) {
			message = e.getLocalizedMessage();
			status = HttpStatus.BAD_REQUEST;
		} catch(Exception e) {
			message = "User Record retrieval failed due to "+e.getLocalizedMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		if(message == null)
			response = new UserResponseDTO(dto);
		else 
			response = new UserResponseDTO(message);
		return new ResponseEntity<UserResponseDTO>(response, status);
	}
	
}
