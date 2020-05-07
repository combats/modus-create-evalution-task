package com.modus.create.financialtransactions.service.impl;

import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.financialtransactions.dao.FinancialTransactionDao;
import com.modus.create.financialtransactions.entity.FinancialTransaction;
import com.modus.create.financialtransactions.service.FinancialTransactionService;
import com.modus.create.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialTransactionServiceImpl implements FinancialTransactionService {

    private FinancialTransactionDao financialTransactionDao;
    private TimeUtils timeUtils;

    @Autowired
    public FinancialTransactionServiceImpl(FinancialTransactionDao financialTransactionDao, TimeUtils timeUtils) {
        this.financialTransactionDao = financialTransactionDao;
        this.timeUtils = timeUtils;
    }

    @Override
    public FinancialTransaction save(SaveFinancialTransaction financialTransaction) {
        FinancialTransaction transaction = FinancialTransaction.builder()
                .userId(financialTransaction.getUserId())
                .monetaryValue(financialTransaction.getMonetaryValue())
                .type(financialTransaction.getType())
                .creationTime(timeUtils.now())
                .build();
        return financialTransactionDao.save(transaction);
    }
}
