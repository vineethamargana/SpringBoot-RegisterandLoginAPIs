package com.example.ral.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ral.Repo.UserRepo;
import com.example.ral.entity.User;
import com.example.ral.exception.UserAlreadyExistException;

@Service
public class UserService {
	
	@Autowired
	UserRepo ur;
	
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public String registerUser(User user)
	{
		Optional<User> excistinguser = ur.findByEmail(user.getEmail());
		if(excistinguser.isPresent())
		{
			throw new UserAlreadyExistException("Email already exists please login!");
		}
		else
		{
			String hashedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(hashedPassword);
			ur.save(user);
			return "User registered successfully";
		}
		

		
//		Optional<User> exist = ur.findByUsername(user.getUsername());
//		if(exist.isPresent())
//		{
//			System.out.println("register");
//			throw new UserAlreadyExistException("Email already exists please login!");
//		}
//		else
//		{
//			System.out.println("register");
//			ur.save(user);
//			return "User registered successfully";
//		}
		
		
	}

	public String loginUser(User user) 
	{
		Optional<User> excistinguser = ur.findByUsername(user.getUsername());
		if(excistinguser.isPresent())
		{
			//if((excistinguser.get().getPassword()).equals(user.getPassword()))
			if(passwordEncoder.matches(user.getPassword(), excistinguser.get().getPassword()))
			{
				return "login successful";
			}
			else
			{
				return "password ismatch";
			}
		}
		else
		{
			return "failed to login";
		}
	/*	
		User excistinguser = ur.findByUsername(user.getUsername());
		if(excistinguser!=null)
		{
		
		   if(excistinguser.getPassword().equals(user.getPassword()))
				{
			      return "login successful";
				}
		   else
		   {
			   return "password mismatch";
		   }
		}
		else
		{
			return "user not found";
		}
		*/
	}
}
