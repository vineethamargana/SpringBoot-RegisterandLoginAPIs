package com.example.ral.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionhandle {
	
	@ExceptionHandler(UserAlreadyExistException.class)
       public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException exception) {
        // Return the exception message with the Conflict status (409)
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
