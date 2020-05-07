package com.modus.create.gateway.service.impl;

import com.google.gson.Gson;
import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.gateway.service.FinancialTransactionService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialTransactionServiceImpl implements FinancialTransactionService {
    private static final String SAVE_FINANCIAL_TRANSACTION_QUEUE_NAME = "com.modus.create.financial.transactions";
    private static final String CHANGE_BALANCE_QUEUE_NAME = "com.modus.create.financial.balance.change";

    private RabbitTemplate rabbitTemplate;
    private Gson gson;

    @Autowired
    public FinancialTransactionServiceImpl(RabbitTemplate rabbitTemplate, Gson gson) {
        this.rabbitTemplate = rabbitTemplate;
        this.gson = gson;
    }

    @Override
    public void makeTransaction(SaveFinancialTransaction transaction) {
        rabbitTemplate.convertAndSend(SAVE_FINANCIAL_TRANSACTION_QUEUE_NAME, gson.toJson(transaction));
        rabbitTemplate.convertAndSend(CHANGE_BALANCE_QUEUE_NAME, gson.toJson(transaction));

    }
}
