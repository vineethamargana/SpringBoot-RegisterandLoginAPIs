package com.example.ral.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ral.entity.User;
import com.example.ral.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService us;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
		String message = us.registerUser(user);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
		String message = us.loginUser(email, password);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
//	@PostMapping("/register")
//	public String registerUser( @RequestBody User user)
//	{
//		    boolean isEmailValid = ValidationCode.emailValidation(user.getEmail());
//		    boolean isPasswordValid = ValidationCode.passwordValidation(user.getPassword());
//		    
//
//		    if (!isEmailValid && !isPasswordValid) 
//		    {
//		        return "Please provide a valid email and password.";
//		    } 
//		    else if (!isEmailValid) 
//		    {
//		        return "Please provide a valid email address, e.g., username@example.com.";
//		    } 
//		    else if (!isPasswordValid) 
//		    {
//		        return "Invalid password format";
//		    } 
//		    else 
//		    {
//		        return us.registerUser(user);
//		    }
//	}
	
	
}
