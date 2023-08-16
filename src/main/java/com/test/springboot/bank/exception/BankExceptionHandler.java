package com.test.springboot.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.test.springboot.dto.ExceptionDTO;

@ControllerAdvice
public class BankExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<ExceptionDTO> handleException(Exception ex) {
		ExceptionDTO exp = new ExceptionDTO("500",ex.getMessage(),"Exception");
		return new ResponseEntity<ExceptionDTO>(exp,HttpStatus.OK);
		
	}
}
