package com.example.VideoShareLibraryApplication.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.VideoShareLibraryApplication.model.Detail;
import com.example.VideoShareLibraryApplication.model.User;


public interface DetailRepo extends JpaRepository<Detail, Integer>{

	@Query("from Detail where userId = ?1 and videoId = ?2")
	Collection<Detail> ValidUser(int userId, int id);

	@Query("Select SUM(likeCount) from Detail where videoId = ?1")
	int TotalLike(int id);

	@Query("Select SUM(dislikeCount) from Detail where videoId = ?1")
	int TotalDislike(int videoIds);
	
	@Query("Select userName from Detail where videoId = ?1 and likeCount = ?2")
	List<String> findLikedUserList(int videoIds, int i);

	@Query("Select userName from Detail where videoId = ?1 and dislikeCount = ?2")
	List<String> findDislikedUserList(int videoIds, int i);
	

}
