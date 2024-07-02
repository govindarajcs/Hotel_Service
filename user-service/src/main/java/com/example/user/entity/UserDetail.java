package com.example.user.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_details database table.
 * 
 */
@Entity
@Table(name="user_details")
@NamedQuery(name="UserDetail.findAll", query="SELECT u FROM UserDetail u")
public class UserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USER_DETAILS_USERID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_DETAILS_USERID_GENERATOR")
	@Column(name="user_id")
	private int userId;

	private int age;

	@Column(name="email_id")
	private String emailId;

	private String name;

	private String password;

	@Column(name="phone_no")
	private String phoneNo;

	@Column(name="user_role")
	private String userRole;

	//bi-directional many-to-one association to UserHistory
	@OneToMany(mappedBy="userDetail")
	private List<UserHistory> userHistories;

	public UserDetail() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public List<UserHistory> getUserHistories() {
		return this.userHistories;
	}

	public void setUserHistories(List<UserHistory> userHistories) {
		this.userHistories = userHistories;
	}

	public UserHistory addUserHistory(UserHistory userHistory) {
		getUserHistories().add(userHistory);
		userHistory.setUserDetail(this);

		return userHistory;
	}

	public UserHistory removeUserHistory(UserHistory userHistory) {
		getUserHistories().remove(userHistory);
		userHistory.setUserDetail(null);

		return userHistory;
	}

}