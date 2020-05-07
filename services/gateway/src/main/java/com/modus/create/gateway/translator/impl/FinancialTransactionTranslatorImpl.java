package com.modus.create.gateway.translator.impl;

import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.gateway.command.MakeFinancialTransaction;
import com.modus.create.gateway.translator.FinancialTransactionTranslator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FinancialTransactionTranslatorImpl implements FinancialTransactionTranslator {
    @Override
    public SaveFinancialTransaction translate(MakeFinancialTransaction makeFinancialTransaction, UUID userId) {
        SaveFinancialTransaction saveFinancialTransaction = SaveFinancialTransaction.builder()
                .userId(userId)
                .monetaryValue(makeFinancialTransaction.getMonetaryValue())
                .type(makeFinancialTransaction.getType())
                .build();
        return saveFinancialTransaction;
    }
}
