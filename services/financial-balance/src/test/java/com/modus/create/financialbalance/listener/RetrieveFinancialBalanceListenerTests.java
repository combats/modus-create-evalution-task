package com.modus.create.financialbalance.listener;

import com.google.gson.Gson;
import com.modus.create.financial.ballance.query.RetrieveFinancialBalance;
import com.modus.create.financial.ballance.query.RetrievedFinancialBalance;
import com.modus.create.financialbalance.listener.impl.RetrieveFinancialBalanceListenerImpl;
import com.modus.create.financialbalance.service.FinancialBalanceRetrieverService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class RetrieveFinancialBalanceListenerTests {

    @Mock
    private FinancialBalanceRetrieverService financialBalanceChangerService;

    @Mock
    private RabbitAdmin rabbitAdmin;

    private Gson gson = new Gson();

    @InjectMocks
    private RetrieveFinancialBalanceListener retrieveFinancialBalanceListener = new RetrieveFinancialBalanceListenerImpl(rabbitAdmin, financialBalanceChangerService, gson);

    @Test
    void handle_shouldChangeBalance() {
        UUID userId = UUID.randomUUID();

        RetrievedFinancialBalance retrievedFinancialBalance = new RetrievedFinancialBalance(15);
        when(financialBalanceChangerService.retrieveBalance(new RetrieveFinancialBalance(userId))).thenReturn(retrievedFinancialBalance);

        String result = retrieveFinancialBalanceListener.handle(gson.toJson(new RetrieveFinancialBalance(userId)));

        assertEquals(gson.toJson(retrievedFinancialBalance), result);
    }
}
