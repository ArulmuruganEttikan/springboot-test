package com.test.springboot.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.springboot.bank.entity.Customer;

@Repository
public interface CustomerServiceDAO extends JpaRepository<Customer, Long> {

}
