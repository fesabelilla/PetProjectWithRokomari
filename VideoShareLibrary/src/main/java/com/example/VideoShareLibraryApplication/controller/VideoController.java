package com.example.VideoShareLibraryApplication.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.VideoShareLibraryApplication.model.Login;
import com.example.VideoShareLibraryApplication.model.User;
import com.example.VideoShareLibraryApplication.model.Video;
import com.example.VideoShareLibraryApplication.repository.UserRepo;
import com.example.VideoShareLibraryApplication.repository.VideoRepo;

@Controller
@SessionAttributes("SessionId")
public class VideoController {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	VideoRepo videoRepo;
	
	HttpServletRequest request;
	String sessionId;
    
	int userId;
	
	
	@GetMapping("/dashboard")
	private String dashboard(Model model,Video video){
		if(model.getAttribute("SessionId") == null) {
			return "pageNotFound.html";
		}
		else {
			return "addVideo.html";
		}
		
	}
	
	@PostMapping("/addVideo")
	private String addVideo(@Valid @ModelAttribute Video video, BindingResult bindingResult, Model model,
			HttpServletRequest request,RedirectAttributes redirectAttributes) {
		
		try {
				sessionId = (String) request.getSession().getAttribute("SessionId");
				userId = Integer.valueOf(sessionId);
				Collection<User> user = userRepo.findByuserId(userId);
				
				video.setUserId(userId);
				video.setUserName(user.iterator().next().getFullName());
				video.setLikeCount(0);
				video.setDislikeCount(0);
				// video count
				video.setViewCount(0);
				
				videoRepo.save(video);
				
				redirectAttributes.addFlashAttribute("message", "Video Added Successfully!!");
			    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				
				return "redirect:/home";
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Exp");
			return "addVideo.html";
		}
		
	}
	
	

}
