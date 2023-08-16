package com.test.springboot.bank.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.springboot.bank.dao.AccountServiceDAO;
import com.test.springboot.bank.dao.AuditServiceDAO;
import com.test.springboot.bank.dao.TransactionServiceDAO;
import com.test.springboot.bank.entity.Account;
import com.test.springboot.bank.entity.AuditDetail;
import com.test.springboot.bank.entity.Transaction;
import com.test.springboot.dto.TransactionDTO;
import com.test.springboot.util.EntityConverter;

@Service
public class TransactionServiceImpl {
	
	public final static Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	AccountServiceDAO accountServiceDAO;

	@Autowired
	TransactionServiceDAO transactionServiceDAO;
	
	@Autowired
	AuditServiceDAO auditServiceDAO;

	public String performTransaction(TransactionDTO transDto) throws Exception {
		
		Optional<Account> fromAcct = accountServiceDAO.accountDetails(transDto.getFromAccountNumber());
		if(!fromAcct.isPresent()) {
			logger.info("From Account Not Available for Transaction");
			throw new Exception("From Account Not Available for Transaction");
		}
		Optional<Account> toAcct = accountServiceDAO.accountDetails(transDto.getToAccountNumber());
		
		if (!transDto.getType().equalsIgnoreCase("deposit")) {
			if ( checkBalance(fromAcct,transDto) ) {
				if(transDto.getType().equalsIgnoreCase("withdrawl")) {
					fromAcct.get().setBalance(fromAcct.get().getBalance() - transDto.getAmount());
					accountServiceDAO.save(fromAcct.get());
					transactionServiceDAO.save(EntityConverter.TransDtotoEntity(fromAcct.get(),null,transDto));
					logger.info("withdrawl completed successfully");
					auditServiceDAO.save(new AuditDetail("Amt_Withdrawl","Balance Withdrawl Performed",new Date()));
				} else if (transDto.getType().equalsIgnoreCase("transfer")) {
					if (!toAcct.isPresent()) {
						logger.info("TO Account Not Available for Transfer");
						throw new Exception("TO Account Not Available for Transfer");
					}
					toAcct.get().setBalance(toAcct.get().getBalance() + transDto.getAmount());
					accountServiceDAO.save(toAcct.get());
					
					fromAcct.get().setBalance(fromAcct.get().getBalance() - transDto.getAmount());
					accountServiceDAO.save(fromAcct.get());
					
					transactionServiceDAO.save(EntityConverter.TransDtotoEntity(fromAcct.get(),toAcct.get(),transDto));
					auditServiceDAO.save(new AuditDetail("Amt_Transfer","Balance Transfer Performed",new Date()));
				}
			} else {
				logger.info("Required balance Not Available for Transaction");
				throw new Exception("Required balance Not Available for Transaction");
			}
		} else {
			fromAcct.get().setBalance(fromAcct.get().getBalance() + transDto.getAmount());
			accountServiceDAO.save(fromAcct.get());
			transactionServiceDAO.save(EntityConverter.TransDtotoEntity(fromAcct.get(),null,transDto));
			auditServiceDAO.save(new AuditDetail("Amt_Deposit","Balance Deposit Performed",new Date()));
		}
		return transDto.getType() + " successfully completed";
	}

	private boolean checkBalance(Optional<Account> fromAcct, TransactionDTO transDto) {
		
		boolean res = false;
		if(fromAcct.isPresent()) {
			res = fromAcct.get().getBalance() >=  transDto.getAmount() ? true : false;
		} 
		return res;
	}

	public List<TransactionDTO> generateStatements(Long accountId) throws Exception {
		List<TransactionDTO> listResp = new ArrayList<TransactionDTO>();
		Optional<List<Transaction>> listTrans = transactionServiceDAO.transactionsDetails(accountId);
		if (listTrans.isPresent() && !listTrans.isEmpty()) {
			for (Transaction obj : listTrans.get()) {
				TransactionDTO dto = new TransactionDTO();
				dto = EntityConverter.TransEntitytoDto(obj);
				listResp.add(dto);
			}
		}
		auditServiceDAO.save(new AuditDetail("Stmt_Fetch","Statement Generation Performed",new Date()));
		return listResp;
	}
}
