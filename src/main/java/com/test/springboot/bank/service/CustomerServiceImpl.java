package com.test.springboot.bank.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.springboot.bank.dao.AuditServiceDAO;
import com.test.springboot.bank.dao.CustomerServiceDAO;
import com.test.springboot.bank.entity.AuditDetail;
import com.test.springboot.dto.CustomerDTO;
import com.test.springboot.util.EntityConverter;

@Service
public class CustomerServiceImpl {
	
	public final static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	CustomerServiceDAO customerServiceDAO;
	
	@Autowired
	AuditServiceDAO auditServiceDAO;
	
	public String craeteCustomer(CustomerDTO custDto) throws Exception {
		customerServiceDAO.save(EntityConverter.CustDtotoEntity(custDto));
		auditServiceDAO.save(new AuditDetail("Create_Cust","Customer Created",new Date()));
		return "Success";
	}

}
