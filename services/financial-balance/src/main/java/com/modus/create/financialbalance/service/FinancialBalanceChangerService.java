package com.modus.create.financialbalance.service;

import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.financialbalance.entity.FinancialBalance;

public interface FinancialBalanceChangerService {
    FinancialBalance changeBalance(SaveFinancialTransaction financialTransaction);
}
