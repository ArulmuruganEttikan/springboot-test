package com.test.springboot.bank.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.springboot.bank.entity.Account;

@Repository
public interface AccountServiceDAO extends JpaRepository<Account, Long> {
	
	@Query("Select A from Account A WHERE A.accountNumber =:accountNumber")
	Optional<Account> accountDetails(@Param("accountNumber") String accountNumber);

}
