package com.quedacoder.WorkTaskApplication.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.quedacoder.WorkTaskApplication.dto.UserRegistrationDto;
import com.quedacoder.WorkTaskApplication.entities.User;

public interface UserService extends UserDetailsService {
	
	User findByEmail(String email);
	User save(UserRegistrationDto registration);

}
