package com.example.VideoShareLibraryApplication.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.VideoShareLibraryApplication.model.Detail;
import com.example.VideoShareLibraryApplication.model.Login;
import com.example.VideoShareLibraryApplication.model.User;
import com.example.VideoShareLibraryApplication.model.Video;
import com.example.VideoShareLibraryApplication.repository.DetailRepo;
import com.example.VideoShareLibraryApplication.repository.UserRepo;
import com.example.VideoShareLibraryApplication.repository.VideoRepo;



@Controller
@SessionAttributes("SessionId")
public class VideoController {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	VideoRepo videoRepo;
	@Autowired
	DetailRepo detailRepo;
	
	HttpServletRequest request;
	String sessionId;
	Video video;
    
	int userId;
	int videoIds;
	
	String msg = "";
	
	@ModelAttribute
	public void message(Model model) {
		model.addAttribute("msg", msg);
	}
	
	
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
				
				String videoString = video.getVideoLink().substring(32);
				
				video.setVideoLink(videoString);
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
			return "addVideo.html";
		}
		
	}
	
	@GetMapping("/viewVideo")
	private String viewVedio(@RequestParam String videoId,Model model,HttpServletRequest request) {
		video = videoRepo.getById(Integer.parseInt(videoId));
		videoIds = Integer.parseInt(videoId);
		int viewCount = video.getViewCount();
		updateViewCount(viewCount, video,videoId);
		
		model.addAttribute(video);
		
		return "viewVideo.html";
	}
	
	private void updateViewCount(int viewCount,@ModelAttribute Video video,String videoId) {
		
		int id = Integer.parseInt(videoId);
		
		Video viewVideo = videoRepo.getOne(id);
		
		viewCount = viewCount + 1;
		
		viewVideo.setViewCount(viewCount);
		
		videoRepo.save(viewVideo);
		
	}
	
	@PostMapping("/like")
	private String likeVideo(Model model,@ModelAttribute Detail detail,HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if(model.getAttribute("SessionId") == null) {
			return "redirect:/login";
		}
		else {
				sessionId = (String) request.getSession().getAttribute("SessionId");
				userId = Integer.valueOf(sessionId);
				int id = videoIds;
				int likeCount = 1;
				int dislikeCount = 0;
				
				User user = userRepo.getById(userId);
				String userName = user.getFullName();
				
				//int totalSum = detailRepo.TotalSum(id);
				Collection<Detail> userDetail = detailRepo.ValidUser(userId,id);  
				
				if(userDetail.isEmpty()) {
					detail.setDislikeCount(dislikeCount);
					detail.setLikeCount(likeCount);
					detail.setUserId(userId);
					detail.setVideoId(id);
					detail.setUserName(userName);
		
					detailRepo.save(detail);
					
					int totalLike = detailRepo.TotalLike(videoIds);
					System.out.println(totalLike);
					
					Video viewVideo = videoRepo.getOne(id);
					viewVideo.setLikeCount(totalLike);
					videoRepo.save(viewVideo);
					
					
					redirectAttributes.addFlashAttribute("message", "Video Liked");
					redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					
					return "redirect:/home";
				}
				else if(!userDetail.isEmpty() && userDetail.iterator().next().getDislikeCount() == 1) {
					redirectAttributes.addFlashAttribute("message", "You disliked the video before");
					redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
					
					return "redirect:/home";
					
				}
				
				else {
					
					redirectAttributes.addFlashAttribute("message", "You already like the video");
					redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
					
					return "redirect:/home";
					
				}
			
		}
		
	}
	
	@PostMapping("/dislike")
	private String dislikeVideo(Model model,@ModelAttribute Detail detail,HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		if(model.getAttribute("SessionId") == null) {
			return "redirect:/login";
		}
		else {
				sessionId = (String) request.getSession().getAttribute("SessionId");
				userId = Integer.valueOf(sessionId);
				int id = videoIds;
				int likeCount = 0;
				int dislikeCount = 1;
				
				User user = userRepo.getById(userId);
				String userName = user.getFullName();
				
				Collection<Detail> userDetail = detailRepo.ValidUser(userId,id);  
				
				if(userDetail.isEmpty()) {
					detail.setDislikeCount(dislikeCount);
					detail.setLikeCount(likeCount);
					detail.setUserId(userId);
					detail.setVideoId(id);
					detail.setUserName(userName);
		
					detailRepo.save(detail);
					
					int totalDislike = detailRepo.TotalDislike(videoIds);
					System.out.println(totalDislike);
					
					Video viewVideo = videoRepo.getOne(id);
					viewVideo.setDislikeCount(totalDislike);
					videoRepo.save(viewVideo);
					
					
					redirectAttributes.addFlashAttribute("message", "Video Disliked");
					redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
					
					return "redirect:/home";
				}
				
				
				else if(!userDetail.isEmpty() && userDetail.iterator().next().getLikeCount() == 1) {
					
					redirectAttributes.addFlashAttribute("message", "You liked the video before");
					redirectAttributes.addFlashAttribute("alertClass", "alert-success");
					
					return "redirect:/home";
					
				}
				else {
					redirectAttributes.addFlashAttribute("message", "You already dislike the video");
					redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
					return "redirect:/home";
				}
			
		}
	
	}
	

}
