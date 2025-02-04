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

	public String registerUser(User user) {
		Optional<User> existingUser = ur.findByEmail(user.getEmail());
		if (existingUser.isPresent()) {
			throw new UserAlreadyExistException("Email already exists. Please login!");
		} else {
			String hashedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(hashedPassword);
			ur.save(user);
			return "User registered successfully!";
		}	
	}

	public String loginUser(String email, String password) {
		Optional<User> existingUser = ur.findByEmail(email);
		if (existingUser.isPresent()) {
			if (passwordEncoder.matches(password, existingUser.get().getPassword())) {
				return "Login successful";
			} else {
				return "Password mismatch";
			}
		} else {
			return "Failed to login: User not found";
		}
	}
}
