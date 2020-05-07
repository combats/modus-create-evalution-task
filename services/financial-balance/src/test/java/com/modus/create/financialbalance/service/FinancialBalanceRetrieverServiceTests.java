package com.modus.create.financialbalance.service;

import com.modus.create.financial.ballance.command.RetrievedFinancialBalance;
import com.modus.create.financialbalance.dao.FinancialBalanceDao;
import com.modus.create.financialbalance.entity.FinancialBalance;
import com.modus.create.financialbalance.service.impl.FinancialBalanceRetrieverServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class FinancialBalanceRetrieverServiceTests {

    @Mock
    private FinancialBalanceDao financialBalanceDao;

    @InjectMocks
    private FinancialBalanceRetrieverService financialBalanceRetrieverService = new FinancialBalanceRetrieverServiceImpl(financialBalanceDao);

    @Test
    void retrieveBalance_balanceRetrieved() {
        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        FinancialBalance currentFinancialBalance = FinancialBalance.builder()
                .id(id)
                .userId(userId)
                .monetaryBalance(15)
                .build();

        when(financialBalanceDao.getByUserId(userId)).thenReturn(currentFinancialBalance);

        RetrievedFinancialBalance actualFinancialBalance = financialBalanceRetrieverService.retrieveBalance(userId);

        assertEquals(15, actualFinancialBalance.getMonetaryBalance());
    }

    @Test
    void retrieveBalance_recordDoesNotExist_zeroBalanceRetrieved() {
        UUID userId = UUID.randomUUID();

        when(financialBalanceDao.getByUserId(userId)).thenReturn(null);

        RetrievedFinancialBalance actualFinancialBalance = financialBalanceRetrieverService.retrieveBalance(userId);

        assertEquals(0, actualFinancialBalance.getMonetaryBalance());
    }

}
