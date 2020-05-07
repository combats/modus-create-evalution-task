package com.modus.create.gateway.translator;

import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.gateway.command.MakeFinancialTransaction;

import java.util.UUID;

public interface FinancialTransactionTranslator {
    SaveFinancialTransaction translate(MakeFinancialTransaction makeFinancialTransaction, UUID userId);
}
