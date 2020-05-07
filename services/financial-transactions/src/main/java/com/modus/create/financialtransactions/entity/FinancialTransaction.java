package com.modus.create.financialtransactions.entity;

import com.modus.create.financial.transactions.command.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinancialTransaction {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID userId;

    private float monetaryValue;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime creationTime;

}
