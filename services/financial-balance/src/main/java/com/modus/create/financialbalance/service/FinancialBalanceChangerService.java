package com.modus.create.financialbalance.service;

import com.modus.create.financial.ballance.command.ChangedFinancialBalance;
import com.modus.create.financial.transactions.command.SaveFinancialTransaction;

public interface FinancialBalanceChangerService {
    ChangedFinancialBalance changeBalance(SaveFinancialTransaction financialTransaction);
}
