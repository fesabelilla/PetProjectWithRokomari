package com.example.VideoShareLibraryApplication.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.VideoShareLibraryApplication.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("from User where email = ?1 and password = ?2")
	Collection<User> ValidUser(String email, String password);

	Collection<User> findByuserId(int userId);
	
}
