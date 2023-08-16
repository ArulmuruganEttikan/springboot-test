package com.test.springboot.bank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.springboot.bank.service.AccountServiceImpl;
import com.test.springboot.dto.AccountDTO;

@RestController           
public class AccountController {
	
	public final static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	AccountServiceImpl accountServiceImpl;

	@PostMapping(value = "/api/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	public String craeteAccount (@RequestBody AccountDTO acctDto) throws Exception {
		String result = accountServiceImpl.craeteAccount(acctDto);
		return result;
	}
	
	@GetMapping(value = "/api/accounts/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AccountDTO getAccount (@PathVariable Long accountId) throws Exception {
		AccountDTO result = accountServiceImpl.getAccount(accountId);
		return result;
	}
}
