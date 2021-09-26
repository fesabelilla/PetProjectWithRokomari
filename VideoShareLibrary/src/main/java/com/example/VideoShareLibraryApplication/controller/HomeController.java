package com.example.VideoShareLibraryApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.VideoShareLibraryApplication.model.User;


@Controller
public class HomeController {
	
	@GetMapping("/home")
	private String homepage(Model model) {
		return "homePage.html";
	}
	
	
}
