package com.jonas.payflow.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferRequest {

    @Schema(example = "1")
    @NotNull
    private Long fromAccountId;

    @Schema(example = "2")
    @NotNull
    private Long toAccountId;

    @Schema(example = "50.00")
    @NotNull
    @Positive
    private BigDecimal amount;
}
