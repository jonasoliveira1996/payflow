package com.jonas.payflow.application.controller;

import com.jonas.payflow.application.dto.BalanceResponse;
import com.jonas.payflow.application.service.AccountService;
import com.jonas.payflow.domain.model.Account;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Saldo")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Consulta de saldo realizada com sucsso"),
        @ApiResponse(responseCode = "400", description = "Erro de regra de negócio"),
        @ApiResponse(responseCode = "422", description = "Erro de validação")
})
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(
            summary = "Consultar saldo da conta",
            description = "Realizar consulta de saldo"
    )
    @GetMapping("/{id}/balance")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable Long id) {
        Account account = accountService.findById(id);

        return ResponseEntity.ok(
                new BalanceResponse(
                        account.getId(),
                        account.getBalance()));
    }
}
