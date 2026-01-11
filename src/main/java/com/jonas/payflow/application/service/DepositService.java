package com.jonas.payflow.application.service;

import com.jonas.payflow.domain.model.Account;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DepositService {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public DepositService(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Transactional
    public void deposit(Long accountId,
                        BigDecimal amount) {

        Account account = accountService.findById(accountId);

        account.setBalance((account.getBalance().add(amount)));

        transactionService.createCredit(account, amount);
    }
}
