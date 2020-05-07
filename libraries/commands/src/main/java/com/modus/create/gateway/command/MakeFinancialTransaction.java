package com.modus.create.gateway.command;

import com.modus.create.financial.transactions.command.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakeFinancialTransaction {

    @Min(value = 1)
    private int monetaryValue;

    private TransactionType type;
}
