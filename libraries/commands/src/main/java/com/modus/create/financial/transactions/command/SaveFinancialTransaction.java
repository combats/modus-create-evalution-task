package com.modus.create.financial.transactions.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveFinancialTransaction {

    private UUID userId;

    @Min(value = 1)
    private int monetaryValue;

    private TransactionType type;
}
