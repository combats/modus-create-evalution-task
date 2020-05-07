package com.modus.create.financialbalance.service;

import com.modus.create.financial.ballance.query.RetrieveFinancialBalance;
import com.modus.create.financial.ballance.query.RetrievedFinancialBalance;

public interface FinancialBalanceRetrieverService {
    RetrievedFinancialBalance retrieveBalance(RetrieveFinancialBalance retrieveFinancialBalance);
}
