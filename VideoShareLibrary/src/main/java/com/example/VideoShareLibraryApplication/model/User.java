package com.example.VideoShareLibraryApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private int userId;
	
	@NotEmpty(message =  "Must not be empty")
	@Size(min = 5, message = "The Full Name should have at least 5 characters")
	private String fullName;
	
	@NotEmpty(message =  "Must not be empty")
	@Column(unique=true)
	@Size(min = 11, max = 11, message = "Phone number should be 11 digits")
	private String phoneNumber;
	
	@NotEmpty(message =  "Must not be empty")
	private String address;
	
	@NotEmpty(message =  "Must not be empty")
	@Column(unique=true)
	private String email;
	
	@NotEmpty(message =  "Must not be empty")
	@Size(min = 8, message = "The Full Name should have at least 8 characters")
	private String password;
	
	public User() {
		super();
	}

	public User(int userId,
			@NotEmpty(message = "Must not be empty") @Size(min = 5, message = "The Full Name should have at least 5 characters") String fullName,
			@NotEmpty(message = "Must not be empty") @Size(min = 11, max = 11, message = "Phone number should be 11 digits") String phoneNumber,
			@NotEmpty(message = "Must not be empty") String address,
			@NotEmpty(message = "Must not be empty") String email,
			@NotEmpty(message = "Must not be empty") @Size(min = 8, message = "The Full Name should have at least 8 characters") String password) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
