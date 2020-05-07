package com.modus.create.financialbalance.service;

import com.modus.create.financial.ballance.command.RetrievedFinancialBalance;

import java.util.UUID;

public interface FinancialBalanceRetrieverService {
    RetrievedFinancialBalance retrieveBalance(UUID userId);
}
