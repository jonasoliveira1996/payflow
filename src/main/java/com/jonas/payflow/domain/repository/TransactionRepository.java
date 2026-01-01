package com.jonas.payflow.domain.repository;

import com.jonas.payflow.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
