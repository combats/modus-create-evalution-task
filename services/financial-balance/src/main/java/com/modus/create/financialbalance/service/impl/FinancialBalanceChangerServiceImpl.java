package com.modus.create.financialbalance.service.impl;

import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.financial.transactions.command.TransactionType;
import com.modus.create.financialbalance.dao.FinancialBalanceDao;
import com.modus.create.financialbalance.entity.FinancialBalance;
import com.modus.create.financialbalance.service.FinancialBalanceChangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialBalanceChangerServiceImpl implements FinancialBalanceChangerService {

    private FinancialBalanceDao financialBalanceDao;

    @Autowired
    public FinancialBalanceChangerServiceImpl(FinancialBalanceDao financialBalanceDao) {
        this.financialBalanceDao = financialBalanceDao;
    }

    @Override
    public FinancialBalance changeBalance(SaveFinancialTransaction financialTransaction) {
        FinancialBalance currentBalance = financialBalanceDao.getByUserId(financialTransaction.getUserId());
        if(currentBalance == null) {
            currentBalance = FinancialBalance.builder()
                    .monetaryBalance(0)
                    .userId(financialTransaction.getUserId())
                    .build();
        }

        if(financialTransaction.getType() == TransactionType.INCOME) {
            currentBalance.setMonetaryBalance(currentBalance.getMonetaryBalance() + financialTransaction.getMonetaryValue());
        }
        if(financialTransaction.getType() == TransactionType.EXPENSE) {
            currentBalance.setMonetaryBalance(currentBalance.getMonetaryBalance() - financialTransaction.getMonetaryValue());
        }

        return financialBalanceDao.save(currentBalance);
    }
}
