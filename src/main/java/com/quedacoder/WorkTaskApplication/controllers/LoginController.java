package com.quedacoder.WorkTaskApplication.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.quedacoder.WorkTaskApplication.dto.UserRegistrationDto;
import com.quedacoder.WorkTaskApplication.entities.User;
import com.quedacoder.WorkTaskApplication.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping("/registration")
	public String registerForm(Model model) {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result) {
		User existing = userService.findByEmail(userDto.getEmail());
		
		if (existing != null) {
			result.rejectValue("email", null, "There is already an account registered with that email address");
		}
		
		if (result.hasErrors()) {
			return "registration";
		}
		
		userService.save(userDto);
		return "redirect:/registration?success";
    }
}
