package com.modus.create.financialbalance.listener;

import com.google.gson.Gson;
import com.modus.create.financial.ballance.command.ChangedFinancialBalance;
import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.financial.transactions.command.SavedFinancialTransaction;
import com.modus.create.financialbalance.listener.impl.ChangeFinancialBalanceListenerImpl;
import com.modus.create.financialbalance.service.FinancialBalanceChangerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.modus.create.financial.transactions.command.TransactionType.INCOME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ChangeFinancialBalanceListenerTests {

    @Mock
    private FinancialBalanceChangerService financialBalanceChangerService;

    @Mock
    private RabbitAdmin rabbitAdmin;

    private Gson gson = new Gson();

    @InjectMocks
    private ChangeFinancialBalanceListener changeFinancialBalanceListener = new ChangeFinancialBalanceListenerImpl(rabbitAdmin, financialBalanceChangerService, gson);

    @Test
    void handle_shouldChangeBalance() {
        UUID userId = UUID.randomUUID();
        LocalDateTime creationTime = LocalDateTime.of(2020, 5, 7, 0, 0, 1);
        SaveFinancialTransaction transactionToSave = SaveFinancialTransaction.builder()
                .userId(userId)
                .monetaryValue(10)
                .type(INCOME)
                .build();

        SavedFinancialTransaction savedFinancialTransaction = new SavedFinancialTransaction(UUID.randomUUID());

        ChangedFinancialBalance changedFinancialBalance = new ChangedFinancialBalance(15);
        when(financialBalanceChangerService.changeBalance(transactionToSave)).thenReturn(changedFinancialBalance);

        String result = changeFinancialBalanceListener.handle(gson.toJson(transactionToSave));

        assertEquals(gson.toJson(changedFinancialBalance), result);
    }
}
