package com.jonas.payflow.application.controller;

import com.jonas.payflow.application.dto.DepositRequest;
import com.jonas.payflow.application.service.DepositService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Depósito")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Depósito realizada com sucsso"),
        @ApiResponse(responseCode = "400", description = "Erro de regra de negócio"),
        @ApiResponse(responseCode = "422", description = "Erro de validação")
})
@RestController
@RequestMapping("/accounts")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @Operation(
            summary = "Efetuar depósito na conta",
            description = "Deposita em uma conta um valor predeterminado"
    )
    @PostMapping("/{id}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable Long id,
                                        @Valid @RequestBody DepositRequest request) {
        depositService.deposit(id, request.getAmount());

        return ResponseEntity.ok().build();
    }
}
