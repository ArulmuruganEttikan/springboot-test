package com.test.springboot.bank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name= "TRANSACTION")
public class Transaction {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "AMOUNT")
	private Double amount;
	
	@Column(name = "TIMESTAMP")
	private Date timeStamp;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Account fromAccount;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Account toAccount;

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	
	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

}
