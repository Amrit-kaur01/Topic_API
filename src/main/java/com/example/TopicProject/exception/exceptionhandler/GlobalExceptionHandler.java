package com.example.TopicProject.exception.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.TopicProject.exception.custom.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = BusinessException.class)
	public ResponseEntity businessException(BusinessException businessException) {
		return new ResponseEntity(businessException.getErrorMessage(),
				HttpStatus.valueOf(businessException.getErrorCode()));
	}
}
