package com.test.springboot.bank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.springboot.bank.service.CustomerServiceImpl;
import com.test.springboot.dto.CustomerDTO;

@RestController           
public class CustomerController {
	
	public final static Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerServiceImpl customerServiceImpl;

	@GetMapping(value = "/api/healthCheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> checkLiveness () throws Exception {
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
	
	@PostMapping(value = "/api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> craeteCustomer (@RequestBody CustomerDTO custDto) throws Exception {
		String result = customerServiceImpl.craeteCustomer(custDto);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
}
