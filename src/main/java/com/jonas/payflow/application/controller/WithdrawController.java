package com.jonas.payflow.application.controller;

import com.jonas.payflow.application.dto.WithdrawRequest;
import com.jonas.payflow.application.service.WithdrawService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Saque")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Saque efetudado com sucsso"),
        @ApiResponse(responseCode = "400", description = "Erro de regra de negócio"),
        @ApiResponse(responseCode = "422", description = "Erro de validação")
})
@RestController
@RequestMapping("/accounts")
public class WithdrawController {

    private final WithdrawService withdrawService;

    public WithdrawController(WithdrawService withdrawService) {
        this.withdrawService = withdrawService;
    }

    @Operation(
            summary = "Efetuar saque na conta",
            description = "Realiza saque em uma conta com um valor predeterminado"
    )
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Void> withdraw(@PathVariable Long id,
                                         @Valid @RequestBody WithdrawRequest request
    ) {
        withdrawService.withdraw(id, request.getAmount());
        return ResponseEntity.ok().build();
    }
}
