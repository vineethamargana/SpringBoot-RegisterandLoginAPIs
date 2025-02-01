package com.example.ral.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ral.entity.User;
import com.example.ral.service.UserService;
import com.example.ral.validation.ValidationCode;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService us;
	
	@PostMapping("/register")
	public String registerUser(@RequestBody User user)
	{
		    boolean isEmailValid = ValidationCode.emailValidation(user.getEmail());
		    boolean isPasswordValid = ValidationCode.passwordValidation(user.getPassword());
		    

		    if (!isEmailValid && !isPasswordValid) 
		    {
		        return "Please provide a valid email and password.";
		    } 
		    else if (!isEmailValid) 
		    {
		        return "Please provide a valid email address, e.g., username@example.com.";
		    } 
		    else if (!isPasswordValid) 
		    {
		        return "Invalid password format";
		    } 
		    else 
		    {
		        return us.registerUser(user);
		    }
	}
	
	@GetMapping("/login")
	public String loginuser(@RequestBody User user)
	{
		return us.loginUser(user);
		
	}

}
