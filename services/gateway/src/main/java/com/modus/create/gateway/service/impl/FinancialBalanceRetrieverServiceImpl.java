package com.modus.create.gateway.service.impl;

import com.google.gson.Gson;
import com.modus.create.financial.ballance.query.RetrieveFinancialBalance;
import com.modus.create.financial.ballance.query.RetrievedFinancialBalance;
import com.modus.create.gateway.service.FinancialBalanceRetrieverService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialBalanceRetrieverServiceImpl implements FinancialBalanceRetrieverService {
    private static final String RETRIEVE_BALANCE_QUEUE_NAME = "com.modus.create.financial.balance.retrieve";

    private RabbitTemplate rabbitTemplate;
    private Gson gson;

    @Autowired
    public FinancialBalanceRetrieverServiceImpl(RabbitTemplate rabbitTemplate, Gson gson) {
        this.rabbitTemplate = rabbitTemplate;
        this.gson = gson;
    }

    @Override
    public RetrievedFinancialBalance retrieveBalance(RetrieveFinancialBalance retrieveFinancialBalance) {
        String message = (String) rabbitTemplate.convertSendAndReceive(RETRIEVE_BALANCE_QUEUE_NAME, gson.toJson(retrieveFinancialBalance));
        return gson.fromJson(message, RetrievedFinancialBalance.class);
    }
}
