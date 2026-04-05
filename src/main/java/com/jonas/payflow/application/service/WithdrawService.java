package com.jonas.payflow.application.service;

import com.jonas.payflow.exception.BusinessException;
import org.springframework.stereotype.Service;
import com.jonas.payflow.domain.model.Account;
import java.math.BigDecimal;

@Service
public class WithdrawService {

    private final AccountService accountService;
    private final TransactionService transactionService;


    public WithdrawService(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    public void withdraw(Long accountId, BigDecimal amount) {
        Account account = accountService.findById(accountId);

        if (account.getBalance().compareTo(amount) < 0) {
            throw new BusinessException("Saldo insuficiente");
        }

        account.setBalance(account.getBalance().subtract(amount));

        transactionService.createDebit(account, amount);
    }
}
