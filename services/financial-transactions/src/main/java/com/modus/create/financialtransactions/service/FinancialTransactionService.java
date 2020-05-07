package com.modus.create.financialtransactions.service;

import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.financialtransactions.entity.FinancialTransaction;

public interface FinancialTransactionService {

    FinancialTransaction save(SaveFinancialTransaction financialTransaction);

}
