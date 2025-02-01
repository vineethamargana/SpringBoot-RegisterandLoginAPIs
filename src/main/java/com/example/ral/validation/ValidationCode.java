package com.example.ral.validation;

public class ValidationCode {
	
	public static boolean passwordValidation(String password)
	{
		String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";
		return password.matches(regex);
	}
	
	public static boolean emailValidation(String email)
	{
		String regex = "^[a-zA-Z0-9_]+@[a-zA-Z]+.[a-zA-Z]{2,}$";
		return email.matches(regex);
	}

}
