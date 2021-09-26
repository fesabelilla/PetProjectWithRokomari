package com.example.VideoShareLibraryApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.VideoShareLibraryApplication.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	

}
