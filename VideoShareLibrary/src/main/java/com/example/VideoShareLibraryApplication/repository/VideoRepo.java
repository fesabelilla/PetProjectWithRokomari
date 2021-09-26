package com.example.VideoShareLibraryApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.VideoShareLibraryApplication.model.Video;

public interface VideoRepo extends JpaRepository<Video, Integer> {

}
