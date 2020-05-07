package com.modus.create.financialbalance.service.impl;

import com.modus.create.financial.ballance.query.RetrieveFinancialBalance;
import com.modus.create.financial.ballance.query.RetrievedFinancialBalance;
import com.modus.create.financialbalance.dao.FinancialBalanceDao;
import com.modus.create.financialbalance.entity.FinancialBalance;
import com.modus.create.financialbalance.service.FinancialBalanceRetrieverService;
import org.springframework.stereotype.Service;

@Service
public class FinancialBalanceRetrieverServiceImpl implements FinancialBalanceRetrieverService {
    private FinancialBalanceDao financialBalanceDao;

    public FinancialBalanceRetrieverServiceImpl(FinancialBalanceDao financialBalanceDao) {
        this.financialBalanceDao = financialBalanceDao;
    }

    @Override
    public RetrievedFinancialBalance retrieveBalance(RetrieveFinancialBalance retrieveFinancialBalance) {
        FinancialBalance financialBalance = financialBalanceDao.getByUserId(retrieveFinancialBalance.getUserId());
        int monetaryBalance = 0;
        if (financialBalance != null) {
            monetaryBalance = financialBalance.getMonetaryBalance();
        }
        return new RetrievedFinancialBalance(monetaryBalance);
    }
}
