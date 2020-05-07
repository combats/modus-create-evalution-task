package com.modus.create.financialbalance.dao;

import com.modus.create.financialbalance.entity.FinancialBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FinancialBalanceRdbDao implements FinancialBalanceDao {

    @Autowired
    private FinancialBalanceJpaDao financialBalanceJpaDao;

    @Override
    public FinancialBalance save(FinancialBalance financialBalance) {
        return financialBalanceJpaDao.save(financialBalance);
    }

    @Override
    public FinancialBalance getByUserId(UUID userId) {
        return financialBalanceJpaDao.findByUserId(userId);
    }

    interface FinancialBalanceJpaDao extends JpaRepository<FinancialBalance, UUID> {
        FinancialBalance findByUserId(UUID userId);
    }
}
