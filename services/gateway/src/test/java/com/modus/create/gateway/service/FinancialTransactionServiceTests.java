package com.modus.create.gateway.service;

import com.google.gson.Gson;
import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.gateway.service.impl.FinancialTransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.modus.create.financial.transactions.command.TransactionType.INCOME;
import static org.mockito.Mockito.*;

@SpringBootTest
class FinancialTransactionServiceTests {
    private static final String SAVE_FINANCIAL_TRANSACTION_QUEUE_NAME = "com.modus.create.financial.transactions";
    private static final String CHANGE_BALANCE_QUEUE_NAME = "com.modus.create.financial.balance.change";

    @Mock
    private RabbitTemplate rabbitTemplate;
    private Gson gson = new Gson();

    @InjectMocks
    private FinancialTransactionService financialTransactionService = new FinancialTransactionServiceImpl(rabbitTemplate, gson);

    @Test
    void makeTransaction_shouldSendToFinancialTransactionQueue() {
        SaveFinancialTransaction transaction = SaveFinancialTransaction.builder()
                .userId(UUID.randomUUID())
                .monetaryValue(10)
                .type(INCOME)
                .build();
        doNothing().when(rabbitTemplate).convertAndSend(SAVE_FINANCIAL_TRANSACTION_QUEUE_NAME, gson.toJson(transaction));
        financialTransactionService.makeTransaction(transaction);
        verify(rabbitTemplate, times(1)).convertAndSend(SAVE_FINANCIAL_TRANSACTION_QUEUE_NAME, gson.toJson(transaction));
    }

    @Test
    void makeTransaction_shouldSendToChangeBalanceQueue() {
        SaveFinancialTransaction transaction = SaveFinancialTransaction.builder()
                .userId(UUID.randomUUID())
                .monetaryValue(10)
                .type(INCOME)
                .build();
        doNothing().when(rabbitTemplate).convertAndSend(SAVE_FINANCIAL_TRANSACTION_QUEUE_NAME, gson.toJson(transaction));
        doNothing().when(rabbitTemplate).convertAndSend(CHANGE_BALANCE_QUEUE_NAME, gson.toJson(transaction));
        financialTransactionService.makeTransaction(transaction);
        verify(rabbitTemplate, times(1)).convertAndSend(CHANGE_BALANCE_QUEUE_NAME, gson.toJson(transaction));
    }
}
