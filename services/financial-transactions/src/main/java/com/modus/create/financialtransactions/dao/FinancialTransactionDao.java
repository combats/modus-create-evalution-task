package com.modus.create.financialtransactions.dao;

import com.modus.create.financialtransactions.entity.FinancialTransaction;

public interface FinancialTransactionDao {
    FinancialTransaction save(FinancialTransaction financialTransaction);
}
