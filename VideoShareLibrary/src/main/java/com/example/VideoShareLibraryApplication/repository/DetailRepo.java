package com.example.VideoShareLibraryApplication.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.VideoShareLibraryApplication.model.Detail;


public interface DetailRepo extends JpaRepository<Detail, Integer>{
	@Query("from Detail where userId = ?1")
	Collection<Detail> ValidUser(int id);

}
