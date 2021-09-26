package com.example.VideoShareLibraryApplication.controller;

import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.VideoShareLibraryApplication.model.Login;
import com.example.VideoShareLibraryApplication.model.User;
import com.example.VideoShareLibraryApplication.repository.UserRepo;



@Controller
@SessionAttributes("SessionId")
public class HomeController {
	
	@Autowired
	UserRepo userRepo;
	
	HttpServletRequest request;
	String sessionID ;
	
	
	String msg="";

	@ModelAttribute
	public void welcome(Model m) {
		m.addAttribute("msg",msg);
	}
	
	
	@GetMapping("/home")
	private String homepage(Model model) {
		return "homePage.html";
	}
	
	@GetMapping("/login")
	private String logIn(Login login){
			return "login.html";
	}
	
	@PostMapping("/loginForm")
	private String login(@Valid @ModelAttribute Login login, User user,  BindingResult bindingResult,
			HttpServletRequest request, Model model, RedirectAttributes redirectAttributes,ModelMap modelMap) {
		
		if(bindingResult.hasErrors()) {
			return "login.html";
		}
		else {
			Collection<User> userCollections = userRepo.ValidUser(login.getEmail(),login.getPassword());  
			
			if(userCollections.size() == 0) {
				msg = "Wrong Email or Password";
				return "redirect:/login";
			}
			else {
				@SuppressWarnings("unchecked")
				
				String userID = Integer.toString(userCollections.iterator().next().getUserId());
				
				sessionID = (String) request.getSession().getAttribute("SessionId");
				
				if(sessionID == null) {
					sessionID = "";
					request.getSession().setAttribute("SessionId", sessionID);
				}
				
				sessionID = userID;
				request.getSession().setAttribute("SessionId", sessionID);
				
				redirectAttributes.addFlashAttribute("message", "Login Successfully!");
				redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				
				System.out.println(sessionID +" : "+userCollections.iterator().next().getFullName());
				
				return "redirect:/home";
			}
			
			
		}
		
	}
	
	@GetMapping("/logout")
	private String destroySession(HttpServletRequest request,SessionStatus status) {
		status.setComplete();
		request.getSession().invalidate();
		return "redirect:/home";
	}
	
}
