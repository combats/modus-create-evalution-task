package com.modus.create.financialtransactions.service;

import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.financial.transactions.command.SavedFinancialTransaction;

public interface FinancialTransactionService {

    SavedFinancialTransaction save(SaveFinancialTransaction financialTransaction);

}
