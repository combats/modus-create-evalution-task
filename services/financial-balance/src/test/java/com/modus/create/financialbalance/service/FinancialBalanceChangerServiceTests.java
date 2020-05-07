package com.modus.create.financialbalance.service;

import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.financialbalance.dao.FinancialBalanceDao;
import com.modus.create.financialbalance.entity.FinancialBalance;
import com.modus.create.financialbalance.service.impl.FinancialBalanceChangerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.modus.create.financial.transactions.command.TransactionType.EXPENSE;
import static com.modus.create.financial.transactions.command.TransactionType.INCOME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class FinancialBalanceChangerServiceTests {

    @Mock
    private FinancialBalanceDao financialBalanceDao;

    @InjectMocks
    private FinancialBalanceChangerService financialBalanceChangerService = new FinancialBalanceChangerServiceImpl(financialBalanceDao);

    @Test
    void changeBalance_transactionIsIncome_balanceIncreased() {
        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        FinancialBalance currentFinancialBalance = FinancialBalance.builder()
                .id(id)
                .userId(userId)
                .monetaryBalance(15)
                .build();

        SaveFinancialTransaction financialTransaction = SaveFinancialTransaction.builder()
                .userId(userId)
                .monetaryValue(10)
                .type(INCOME)
                .build();

        FinancialBalance expectedFinancialBalance = FinancialBalance.builder()
                .id(id)
                .userId(userId)
                .monetaryBalance(25)
                .build();

        when(financialBalanceDao.getByUserId(userId)).thenReturn(currentFinancialBalance);
        when(financialBalanceDao.save(expectedFinancialBalance)).thenReturn(expectedFinancialBalance);

        FinancialBalance actualFinancialBalance = financialBalanceChangerService.changeBalance(financialTransaction);

        assertEquals(expectedFinancialBalance, actualFinancialBalance);
    }

    @Test
    void changeBalance_transactionIsExpense_balanceDecreased() {
        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        FinancialBalance currentFinancialBalance = FinancialBalance.builder()
                .id(id)
                .userId(userId)
                .monetaryBalance(15)
                .build();

        SaveFinancialTransaction financialTransaction = SaveFinancialTransaction.builder()
                .userId(userId)
                .monetaryValue(10)
                .type(EXPENSE)
                .build();

        FinancialBalance expectedFinancialBalance = FinancialBalance.builder()
                .id(id)
                .userId(userId)
                .monetaryBalance(5)
                .build();

        when(financialBalanceDao.getByUserId(userId)).thenReturn(currentFinancialBalance);
        when(financialBalanceDao.save(expectedFinancialBalance)).thenReturn(expectedFinancialBalance);

        FinancialBalance actualFinancialBalance = financialBalanceChangerService.changeBalance(financialTransaction);

        assertEquals(expectedFinancialBalance, actualFinancialBalance);
    }

    @Test
    void changeBalance_transactionIsIncome_recordDoesNotExist_recordCreated() {
        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        SaveFinancialTransaction financialTransaction = SaveFinancialTransaction.builder()
                .userId(userId)
                .monetaryValue(10)
                .type(INCOME)
                .build();

        FinancialBalance financialBalanceToSave = FinancialBalance.builder()
                .userId(userId)
                .monetaryBalance(10)
                .build();

        FinancialBalance expectedFinancialBalance = FinancialBalance.builder()
                .id(id)
                .userId(userId)
                .monetaryBalance(10)
                .build();

        when(financialBalanceDao.getByUserId(userId)).thenReturn(null);
        when(financialBalanceDao.save(financialBalanceToSave)).thenReturn(expectedFinancialBalance);

        FinancialBalance actualFinancialBalance = financialBalanceChangerService.changeBalance(financialTransaction);

        assertEquals(expectedFinancialBalance, actualFinancialBalance);
    }

}
