package com.jonas.payflow.application.dto;

import java.math.BigDecimal;

public class BalanceResponse {

    private Long accountId;
    private BigDecimal balance;

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BalanceResponse(Long accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }
}
