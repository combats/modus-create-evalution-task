package com.modus.create.gateway.translator;

import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.financial.transactions.command.TransactionType;
import com.modus.create.gateway.command.MakeFinancialTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FinancialTransactionTranslatorTests {

    @Autowired
    private FinancialTransactionTranslator financialTransactionTranslator;

    @Test
    void translate_shouldReturnSaveFinancialTransaction() {
        UUID userId = UUID.randomUUID();
        MakeFinancialTransaction makeFinancialTransaction = MakeFinancialTransaction.builder()
                .monetaryValue(10)
                .type(TransactionType.INCOME)
                .build();

        SaveFinancialTransaction expectedSaveFinancialTransaction = SaveFinancialTransaction.builder()
                .userId(userId)
                .monetaryValue(10)
                .type(TransactionType.INCOME)
                .build();

        SaveFinancialTransaction actualSaveFinancialTransaction = financialTransactionTranslator.translate(makeFinancialTransaction, userId);
        assertEquals(expectedSaveFinancialTransaction, actualSaveFinancialTransaction);
    }
}
