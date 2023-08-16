package com.test.springboot.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.springboot.bank.entity.AuditDetail;

@Repository
public interface AuditServiceDAO extends JpaRepository<AuditDetail, Long> {
	
}
