package com.modus.create.financialtransactions.listener;

import com.google.gson.Gson;
import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.financial.transactions.command.SavedFinancialTransaction;
import com.modus.create.financialtransactions.entity.FinancialTransaction;
import com.modus.create.financialtransactions.listener.impl.FinancialTransactionListenerImpl;
import com.modus.create.financialtransactions.service.FinancialTransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.modus.create.financial.transactions.command.TransactionType.INCOME;
import static org.mockito.Mockito.*;

@SpringBootTest
class FinancialTransactionListenerTests {

    @Mock
    private FinancialTransactionService financialTransactionService;

    @Mock
    private RabbitAdmin rabbitAdmin;

    private Gson gson = new Gson();

    @InjectMocks
    private FinancialTransactionListener financialTransactionListener = new FinancialTransactionListenerImpl(rabbitAdmin, financialTransactionService, gson);

    @Test
    void handle_shouldSave() {
        UUID userId = UUID.randomUUID();
        LocalDateTime creationTime = LocalDateTime.of(2020, 5, 7, 0, 0, 1);
        SaveFinancialTransaction transactionToSave = SaveFinancialTransaction.builder()
                .userId(userId)
                .monetaryValue(10)
                .type(INCOME)
                .build();

        FinancialTransaction expectedSavedTransaction = FinancialTransaction.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .monetaryValue(10)
                .type(INCOME)
                .creationTime(creationTime)
                .build();

        when(financialTransactionService.save(transactionToSave)).thenReturn(new SavedFinancialTransaction(expectedSavedTransaction.getId()));

        financialTransactionListener.handle(gson.toJson(transactionToSave));

        verify(financialTransactionService, times(1)).save(transactionToSave);
    }
}
