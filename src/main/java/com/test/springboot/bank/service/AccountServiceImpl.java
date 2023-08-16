package com.test.springboot.bank.service;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.springboot.bank.dao.AccountServiceDAO;
import com.test.springboot.bank.dao.AuditServiceDAO;
import com.test.springboot.bank.dao.CustomerServiceDAO;
import com.test.springboot.bank.entity.Account;
import com.test.springboot.bank.entity.AuditDetail;
import com.test.springboot.bank.entity.Customer;
import com.test.springboot.dto.AccountDTO;
import com.test.springboot.util.EntityConverter;

@Service
public class AccountServiceImpl {

	public final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	AccountServiceDAO accountServiceDAO;

	@Autowired
	CustomerServiceDAO customerServiceDAO;
	
	@Autowired
	AuditServiceDAO auditServiceDAO;

	public String craeteAccount(AccountDTO acctDto) throws Exception {
		Account ent = EntityConverter.AccountDtotoEntity(acctDto);
		Optional<Customer> cust = customerServiceDAO.findById(acctDto.getCustomerId());
		if (cust.isPresent()) {
			ent.setCustomer(cust.get());
			accountServiceDAO.save(ent);
			auditServiceDAO.save(new AuditDetail("Create_Acct","Account Created",new Date()));
		} else {
			logger.info("craeteAccount - Customer not found");
			throw new Exception("Customer not found");
		}
		return "Account " + ent.getAccountNumber().toString() + " is created successfully";
	}

	public AccountDTO getAccount(Long accountId) throws Exception {
		AccountDTO dto = new AccountDTO();
		Optional<Account> acct = accountServiceDAO.findById(accountId);
		if (acct.isPresent()) {
			dto = EntityConverter.AcctEntitytoDto(acct.get());
		}
		auditServiceDAO.save(new AuditDetail("Search_Acct","Account Search Performed",new Date()));
		return dto;
	}
}
