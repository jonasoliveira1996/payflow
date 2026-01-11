package com.jonas.payflow.application.controller;

import com.jonas.payflow.application.dto.DepositRequest;
import com.jonas.payflow.application.service.DepositService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable Long id,
                                        @Valid @RequestBody DepositRequest request) {
        depositService.deposit(id, request.getAmount());

        return ResponseEntity.ok().build();
    }
}
