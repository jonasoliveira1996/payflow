package com.jonas.payflow.application.service;

import com.jonas.payflow.domain.model.Account;
import com.jonas.payflow.domain.model.Transaction;
import com.jonas.payflow.domain.model.TransactionType;
import com.jonas.payflow.domain.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public  TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createCredit(Account account,
                                    BigDecimal amount) {
        Transaction transaction = new Transaction(account, amount, TransactionType.CREDIT);

        return transactionRepository.save(transaction);
    }

    public Transaction createDebit(Account account, BigDecimal amount) {

        Transaction transaction = new Transaction(account, amount, TransactionType.DEBIT);

        return transactionRepository.save(transaction);
    }
}
