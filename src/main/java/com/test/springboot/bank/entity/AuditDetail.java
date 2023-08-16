package com.test.springboot.bank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name= "AUDIT_DETAIL")
public class AuditDetail {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "EVENT_NAME")
	private String eventName;
	
	@Column(name = "EVENT_DISCRIPTION")
	private String eventDiscription;
	
	@Column(name = "AUDIT_TIMESTAMP")
	private Date auditTimeStamp;
	
	public AuditDetail(String eventName,String eventDiscription,Date auditTimeStamp) {
		this.eventName = eventName;
		this.eventDiscription = eventDiscription;
		this.auditTimeStamp = auditTimeStamp;
	}
	public Long getId() {
		return id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDiscription() {
		return eventDiscription;
	}

	public void setEventDiscription(String eventDiscription) {
		this.eventDiscription = eventDiscription;
	}

	public Date getAuditTimeStamp() {
		return auditTimeStamp;
	}

	public void setAuditTimeStamp(Date auditTimeStamp) {
		this.auditTimeStamp = auditTimeStamp;
	}

}
