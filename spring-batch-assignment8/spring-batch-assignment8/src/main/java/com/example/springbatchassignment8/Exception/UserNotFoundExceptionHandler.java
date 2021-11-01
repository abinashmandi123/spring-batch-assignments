package com.example.springbatchassignment8.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class UserNotFoundExceptionHandler {

	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<Object> exception(UserNotFoundException exception){
		return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
		
	}
}
