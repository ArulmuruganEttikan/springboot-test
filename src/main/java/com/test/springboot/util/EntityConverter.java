package com.test.springboot.util;

import java.util.Date;

import com.test.springboot.bank.entity.Account;
import com.test.springboot.bank.entity.Customer;
import com.test.springboot.bank.entity.Transaction;
import com.test.springboot.dto.AccountDTO;
import com.test.springboot.dto.CustomerDTO;
import com.test.springboot.dto.TransactionDTO;

public class EntityConverter {
	
	public static Customer CustDtotoEntity( CustomerDTO cuatDto) throws Exception {
		
		if (cuatDto == null) {
			throw new Exception("null");
		} 
		 Customer entity = new Customer();
		 entity.setFirstName(cuatDto.getFirstName());
		 entity.setLastName(cuatDto.getLastName());
		 entity.setEmail(cuatDto.getEmail());
		 return entity;
	}
	
	public static Account AccountDtotoEntity( AccountDTO acctDto) throws Exception {
			
			if (acctDto == null) {
				throw new Exception("null");
			} 
			 Account entity = new Account();
			 entity.setAccountNumber(acctDto.getAccountNumber());
			 entity.setBalance(acctDto.getBalance());
			 return entity;
		}
	
	public static AccountDTO AcctEntitytoDto(Account acct) throws Exception {
			
			 AccountDTO dto = new AccountDTO();
			 dto.setAccountNumber(acct.getAccountNumber());
			 dto.setBalance(acct.getBalance());
			 dto.setCustomerId(acct.getCustomer().getId());
			 return dto;
	}
	
	public static Transaction TransDtotoEntity(Account fromAcct, Account toAcct, TransactionDTO transDto) throws Exception {
		
		Transaction entity = new Transaction();
		entity.setType(transDto.getType());
		entity.setAmount(transDto.getAmount());
		entity.setTimeStamp(new Date());
		if (fromAcct != null) {
			entity.setFromAccount(fromAcct);
		}
		if (toAcct != null) {
			entity.setToAccount(toAcct);
		}
		return entity;
	}
	
	public static TransactionDTO TransEntitytoDto(Transaction trans) throws Exception {
		
		TransactionDTO dto = new TransactionDTO();
		 dto.setFromAccountNumber(trans.getFromAccount() != null ? trans.getFromAccount().getAccountNumber() : null);
		 dto.setToAccountNumber(trans.getToAccount() != null ? trans.getToAccount().getAccountNumber() : null);
		 dto.setAmount(trans.getAmount());
		 dto.setType(trans.getType());
		 dto.setTimeStamp(trans.getTimeStamp());
		 return dto;
}

}
