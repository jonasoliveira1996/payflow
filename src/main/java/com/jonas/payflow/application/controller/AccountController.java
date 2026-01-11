package com.jonas.payflow.application.controller;

import com.jonas.payflow.application.dto.BalanceResponse;
import com.jonas.payflow.application.service.AccountService;
import com.jonas.payflow.domain.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable Long id) {
        Account account = accountService.findById(id);

        return ResponseEntity.ok(
                new BalanceResponse(
                        account.getId(),
                        account.getBalance()));
    }
}
