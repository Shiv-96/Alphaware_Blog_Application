package com.assignment.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;

import com.assignment.Entity.User;
import com.assignment.Repository.UserRepository;

public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/api/user/login")
	public ResponseEntity<User> loginHandler(Authentication authentication) {
		
		Optional<User> opt = userRepository.findByEmail(authentication.getName());
		
		if(opt.isPresent()) {
			System.out.println("Hi Shiv");
			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
		} else {
			throw new BadCredentialsException("Invalid Credential");
		}
		
	}
	
}
