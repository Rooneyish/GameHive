package com.gamehive.model;

import java.sql.Date;

public class UserModel {
	private String username;
	private String userEmail;
	private Date dob;
	private String gender;
	private String userRole;
	private String password;
	private Date createdDate;

	public UserModel() {

	}

	public UserModel(String username, String userEmail, Date dob, String gender, String userRole, String password,
			Date createdDate) {
		super();
		this.username = username;
		this.userEmail = userEmail;
		this.dob = dob;
		this.gender = gender;
		this.userRole = userRole;
		this.password = password;
		this.createdDate = createdDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
