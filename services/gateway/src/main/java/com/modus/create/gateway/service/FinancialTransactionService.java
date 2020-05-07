package com.modus.create.gateway.service;

import com.modus.create.financial.transactions.command.SaveFinancialTransaction;

public interface FinancialTransactionService {

    void makeTransaction(SaveFinancialTransaction transaction);

}
