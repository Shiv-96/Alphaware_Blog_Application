package com.assignment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.Entity.User;
import com.assignment.Service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/api/user/register")
	public ResponseEntity<User> registerTheUserHandler(@Valid @RequestBody User user) throws Exception {
		
		String password = user.getPassword();
		
		user.setPassword(passwordEncoder.encode(password));
		
		User registeredUsers = userService.registerUser(user);
		
		return new ResponseEntity<>(registeredUsers, HttpStatus.ACCEPTED);
		
	}
	
	
}
