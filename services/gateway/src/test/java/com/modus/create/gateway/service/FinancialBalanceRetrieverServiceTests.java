package com.modus.create.gateway.service;

import com.google.gson.Gson;
import com.modus.create.financial.ballance.query.RetrieveFinancialBalance;
import com.modus.create.financial.ballance.query.RetrievedFinancialBalance;
import com.modus.create.gateway.service.impl.FinancialBalanceRetrieverServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class FinancialBalanceRetrieverServiceTests {
    private static final String RETRIEVE_BALANCE_QUEUE_NAME = "com.modus.create.financial.balance.retrieve";

    @Mock
    private RabbitTemplate rabbitTemplate;
    private Gson gson = new Gson();

    @InjectMocks
    private FinancialBalanceRetrieverService financialTransactionService = new FinancialBalanceRetrieverServiceImpl(rabbitTemplate, gson);

    @Test
    void makeTransaction_shouldSendToFinancialTransactionQueue() {
        UUID userId = UUID.randomUUID();
        RetrieveFinancialBalance retrieveFinancialBalance = RetrieveFinancialBalance.builder()
                .userId(userId)
                .build();
        RetrievedFinancialBalance expectedFinancialBalance = RetrievedFinancialBalance.builder()
                .monetaryBalance(20)
                .build();
        when(rabbitTemplate.convertSendAndReceive(RETRIEVE_BALANCE_QUEUE_NAME, gson.toJson(retrieveFinancialBalance)))
                .thenReturn(gson.toJson(expectedFinancialBalance));

        RetrievedFinancialBalance retrievedFinancialBalance = financialTransactionService.retrieveBalance(retrieveFinancialBalance);
        assertEquals(expectedFinancialBalance, retrievedFinancialBalance);
    }
}
