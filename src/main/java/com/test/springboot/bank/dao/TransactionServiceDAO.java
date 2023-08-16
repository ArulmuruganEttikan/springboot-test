package com.test.springboot.bank.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.springboot.bank.entity.Transaction;

@Repository
public interface TransactionServiceDAO extends JpaRepository<Transaction, Long> {
	
	@Query("Select T from Transaction T, Account A WHERE T.fromAccount.id = A.id AND A.id =:accountId")
	Optional<List<Transaction>> transactionsDetails(@Param("accountId") Long accountId);

}
