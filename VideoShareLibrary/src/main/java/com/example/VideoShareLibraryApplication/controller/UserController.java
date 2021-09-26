package com.example.VideoShareLibraryApplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.VideoShareLibraryApplication.model.User;
import com.example.VideoShareLibraryApplication.repository.UserRepo;


@Controller
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	
	String msg = "";
	
	@ModelAttribute
	public void message(Model model) {
		model.addAttribute("msg", msg);
	}
	
	@PostMapping("/registrationForm")
	private String registrationForm(@Valid @ModelAttribute User user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		
		try {
			if(bindingResult.hasErrors()) {
				model.addAttribute("name", user.getFullName());
				model.addAttribute("phoneNo",user.getPhoneNumber());
				model.addAttribute("address",user.getAddress());
				model.addAttribute("email",user.getEmail());
				
				return "registration.html";
			}
			else {
				userRepo.save(user);
				
				redirectAttributes.addFlashAttribute("message", "Account created!");
			    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
				
				return "redirect:/home";
			}
			
		} catch (Exception e) {
			msg = "Phone Number or Email used before. Check again and give new one.";
			return "redirect:/registration";
		}
		
	}
	
	@GetMapping("/registration")
	private String userRegistration(User user) {
		msg = "";
		return "registration.html";
	}
	

	
	
}
