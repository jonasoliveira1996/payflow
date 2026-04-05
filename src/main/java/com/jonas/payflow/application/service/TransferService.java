package com.jonas.payflow.application.service;

import com.jonas.payflow.domain.model.Account;
import com.jonas.payflow.exception.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class TransferService {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public TransferService(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Transactional
    public void Transfer(Long fromId, Long toId, BigDecimal amount){

        if (fromId.equals(toId)) {
            throw new BusinessException("Conta de origem e destino não podem ser iguais");
        }

        Account from = accountService.findById(fromId);
        Account to = accountService.findById(toId);

        if (from.getBalance().compareTo(amount) < 0) {
            throw new BusinessException("Saldo insuficiente para transferência");
        }

        // débito

        from.setBalance(from.getBalance().subtract(amount));
        transactionService.createDebit(from, amount);

        // crédito

        to.setBalance(to.getBalance().add(amount));
        transactionService.createCredit(to, amount);
    }

}
