package com.jonas.payflow.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "transactions")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Account account;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    public Transaction(Account account, BigDecimal amount, TransactionType type) {

        if (account == null) {
            throw new IllegalArgumentException("Conta é obrigatória");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da transação deve ser maior que zero");
        }

        if (type == null) {
            throw new IllegalArgumentException("Tipo da transação é obrigatório");
        }

        this.account = account;
        this.amount = amount;
        this.type = type;
    }
}
