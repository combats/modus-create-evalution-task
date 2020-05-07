package com.modus.create.financialbalance.dao;

import com.modus.create.financialbalance.entity.FinancialBalance;

import java.util.UUID;

public interface FinancialBalanceDao {
    FinancialBalance save(FinancialBalance financialBalance);
    FinancialBalance getByUserId(UUID userId);
}
