package com.modus.create.financialtransactions.service;

import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.financialtransactions.dao.FinancialTransactionDao;
import com.modus.create.financialtransactions.entity.FinancialTransaction;
import com.modus.create.financialtransactions.service.impl.FinancialTransactionServiceImpl;
import com.modus.create.utils.TimeUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.modus.create.financial.transactions.command.TransactionType.INCOME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FinancialTransactionServiceTests {

    @Mock
    private FinancialTransactionDao financialTransactionDao;
    @Mock
    private TimeUtils timeUtils;

    @InjectMocks
    private FinancialTransactionService financialTransactionService = new FinancialTransactionServiceImpl(financialTransactionDao, timeUtils);

    @Test
    public void save_shouldSaveFinancialTransaction_withCreationTime() {

        LocalDateTime creationTime = LocalDateTime.of(2020, 5, 7, 0, 0, 1);
        when(timeUtils.now()).thenReturn(creationTime);
        UUID userId = UUID.randomUUID();
        FinancialTransaction transaction = FinancialTransaction.builder()
                .userId(userId)
                .monetaryValue(10.0f)
                .type(INCOME)
                .creationTime(creationTime)
                .build();

        FinancialTransaction expectedSavedTransaction = FinancialTransaction.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .monetaryValue(10.0f)
                .type(INCOME)
                .creationTime(creationTime)
                .build();

        when(financialTransactionDao.save(transaction)).thenReturn(expectedSavedTransaction);

        FinancialTransaction actualSavedTransaction = financialTransactionService.save(
                SaveFinancialTransaction.builder()
                        .userId(userId)
                        .monetaryValue(10.0f)
                        .type(INCOME)
                        .build()
        );

        verify(financialTransactionDao, times(1)).save(transaction);
        assertEquals(expectedSavedTransaction, actualSavedTransaction);
    }

}
