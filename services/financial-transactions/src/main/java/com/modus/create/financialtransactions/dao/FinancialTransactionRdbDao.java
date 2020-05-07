package com.modus.create.financialtransactions.dao;

import com.modus.create.financialtransactions.entity.FinancialTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FinancialTransactionRdbDao implements FinancialTransactionDao {

    @Autowired
    private FinancialTransactionJpaDao financialTransactionJpaDao;

    @Override
    public FinancialTransaction save(FinancialTransaction financialTransaction) {
        return financialTransactionJpaDao.save(financialTransaction);
    }

    interface FinancialTransactionJpaDao extends JpaRepository<FinancialTransaction, UUID> {
    }
}
