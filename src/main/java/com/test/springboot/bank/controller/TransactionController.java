package com.test.springboot.bank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.springboot.bank.service.TransactionServiceImpl;
import com.test.springboot.dto.TransactionDTO;

@RestController           
public class TransactionController {
	
	public final static Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	TransactionServiceImpl transactionServiceImpl;

	@PostMapping(value = "/api/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
	public String performTransaction (@RequestBody TransactionDTO transDto) throws Exception {
		String result = transactionServiceImpl.performTransaction(transDto);
		return result;
	}
	
	@GetMapping(value = "/api/statements/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TransactionDTO> generateStatemnets (@PathVariable Long accountId) throws Exception {
		List<TransactionDTO> result = transactionServiceImpl.generateStatements(accountId);
		return result;
	}
}
