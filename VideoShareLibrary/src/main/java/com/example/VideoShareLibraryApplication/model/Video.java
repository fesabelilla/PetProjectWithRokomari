package com.example.VideoShareLibraryApplication.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int videoId;
	@NotNull
	private int userId;
	
	@NotEmpty(message =  "Must not be empty")
	private String videoTitle;
	
	@NotEmpty(message =  "Must not be empty")
	private String videoLink;
	
	private int viewCount;
	private int likeCount;
	private int dislikeCount;
	@NotNull
	private String userName;
	
	public Video() {
		super();
	}

	public Video(int videoId, @NotNull int userId, @NotEmpty(message = "Must not be empty") String videoTitle,
			@NotEmpty(message = "Must not be empty") String videoLink, int viewCount, int likeCount, int dislikeCount,
			@NotNull String userName) {
		super();
		this.videoId = videoId;
		this.userId = userId;
		this.videoTitle = videoTitle;
		this.videoLink = videoLink;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.userName = userName;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
		
}
