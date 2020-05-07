package com.modus.create.financial.ballance.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetrievedFinancialBalance {
    private int monetaryBalance;
}
