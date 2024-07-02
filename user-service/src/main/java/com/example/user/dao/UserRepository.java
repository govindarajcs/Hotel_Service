package com.example.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user.entity.UserDetail;

public interface UserRepository extends JpaRepository<UserDetail, Integer> {
	
	public UserDetail findByEmailId(String emailId);
	
	public UserDetail findByPhoneNo(String phoneNo);

}
