package com.jonas.payflow.application.service;

import com.jonas.payflow.domain.model.Account;
import com.jonas.payflow.domain.model.User;
import com.jonas.payflow.domain.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccountFor(User user) {
        Account account = new Account();
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);

        return accountRepository.save(account);
    }

    public Account findById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Conta não encontratada"));
    }

    @Transactional
    public void deposit(Long accountId,
                        BigDecimal amount) {

        Account account = findById(accountId);

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);

        accountRepository.save(account);
    }
}
