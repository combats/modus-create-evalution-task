package com.modus.create.financialbalance.listener.impl;

import com.google.gson.Gson;
import com.modus.create.financial.ballance.query.RetrieveFinancialBalance;
import com.modus.create.financial.ballance.query.RetrievedFinancialBalance;
import com.modus.create.financialbalance.listener.RetrieveFinancialBalanceListener;
import com.modus.create.financialbalance.service.FinancialBalanceRetrieverService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RetrieveFinancialBalanceListenerImpl implements RetrieveFinancialBalanceListener {
    private static final String QUEUE_NAME = "com.modus.create.financial.balance.retrieve";

    private RabbitAdmin rabbitAdmin;
    private FinancialBalanceRetrieverService financialBalanceRetrieverService;
    private Gson gson;

    @Autowired
    public RetrieveFinancialBalanceListenerImpl(RabbitAdmin rabbitAdmin, FinancialBalanceRetrieverService financialBalanceRetrieverService, Gson gson) {
        this.rabbitAdmin = rabbitAdmin;
        this.financialBalanceRetrieverService = financialBalanceRetrieverService;
        this.gson = gson;
    }

    @PostConstruct
    public void initQueue() {
        rabbitAdmin.declareQueue(new Queue(QUEUE_NAME));
    }


    @Override
    @RabbitListener(queues = QUEUE_NAME)
    public String handle(String message) {
        RetrieveFinancialBalance retrieveFinancialBalance = gson.fromJson(message, RetrieveFinancialBalance.class);
        RetrievedFinancialBalance retrievedFinancialBalance = financialBalanceRetrieverService.retrieveBalance(retrieveFinancialBalance);
        return gson.toJson(retrievedFinancialBalance);
    }
}
