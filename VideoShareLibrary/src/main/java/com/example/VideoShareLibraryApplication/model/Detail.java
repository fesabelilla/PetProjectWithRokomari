package com.example.VideoShareLibraryApplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Detail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private int userId;
	@NotNull
	private String userName;
	@NotNull
	private int videoId;
	@NotNull
	private int likeCount;
	@NotNull
	private int dislikeCount;
	
	public Detail() {
		super();
	}

	public Detail(int id, @NotNull int userId, @NotNull String userName, @NotNull int videoId, @NotNull int likeCount,
			@NotNull int dislikeCount) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.videoId = videoId;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getDislikeCount() {
		return dislikeCount;
	}

	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}
	
	
	
}
