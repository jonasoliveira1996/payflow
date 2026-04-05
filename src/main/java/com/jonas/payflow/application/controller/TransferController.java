package com.jonas.payflow.application.controller;

import com.jonas.payflow.application.dto.TransferRequest;
import com.jonas.payflow.application.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Transferências")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transferência realizada com sucsso"),
        @ApiResponse(responseCode = "400", description = "Erro de regra de negócio"),
        @ApiResponse(responseCode = "422", description = "Erro de validação")
})
@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @Operation(
            summary = "Transferir valor entre contas",
            description = "Debita uma conta origem e credita uma conta de destino de forma atômica"
    )
    @PostMapping
    public ResponseEntity<Void> transfer(@Valid @RequestBody TransferRequest request) {
        transferService.Transfer(request.getFromAccountId(), request.getToAccountId(), request.getAmount());

        return ResponseEntity.ok().build();
    }
}
