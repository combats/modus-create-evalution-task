package com.modus.create.financialbalance.listener.impl;

import com.google.gson.Gson;
import com.modus.create.financial.ballance.command.ChangedFinancialBalance;
import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.financialbalance.listener.ChangeFinancialBalanceListener;
import com.modus.create.financialbalance.service.FinancialBalanceChangerService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ChangeFinancialBalanceListenerImpl implements ChangeFinancialBalanceListener {
    private static final String QUEUE_NAME = "com.modus.create.financial.balance.change";

    private RabbitAdmin rabbitAdmin;
    private FinancialBalanceChangerService financialBalanceChangerService;
    private Gson gson;

    @Autowired
    public ChangeFinancialBalanceListenerImpl(RabbitAdmin rabbitAdmin, FinancialBalanceChangerService financialBalanceChangerService, Gson gson) {
        this.rabbitAdmin = rabbitAdmin;
        this.financialBalanceChangerService = financialBalanceChangerService;
        this.gson = gson;
    }

    @PostConstruct
    public void initQueue() {
        rabbitAdmin.declareQueue(new Queue(QUEUE_NAME));
    }

    @Override
    @RabbitListener(queues = QUEUE_NAME)
    public String handle(String message) {
        SaveFinancialTransaction transaction = gson.fromJson(message, SaveFinancialTransaction.class);
        ChangedFinancialBalance changedFinancialBalance = financialBalanceChangerService.changeBalance(transaction);
        return gson.toJson(changedFinancialBalance);
    }
}
