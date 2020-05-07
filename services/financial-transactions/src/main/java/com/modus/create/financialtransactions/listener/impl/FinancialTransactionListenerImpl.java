package com.modus.create.financialtransactions.listener.impl;

import com.google.gson.Gson;
import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.financialtransactions.entity.FinancialTransaction;
import com.modus.create.financialtransactions.listener.FinancialTransactionListener;
import com.modus.create.financialtransactions.service.FinancialTransactionService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FinancialTransactionListenerImpl implements FinancialTransactionListener {
    private static final String QUEUE_NAME = "com.modus.create.financial.transactions";

    private RabbitAdmin rabbitAdmin;
    private FinancialTransactionService financialTransactionService;
    private Gson gson;

    @Autowired
    public FinancialTransactionListenerImpl(RabbitAdmin rabbitAdmin, FinancialTransactionService financialTransactionService, Gson gson) {
        this.rabbitAdmin = rabbitAdmin;
        this.financialTransactionService = financialTransactionService;
        this.gson = gson;
    }

    @PostConstruct
    public void initQueue() {
        rabbitAdmin.declareQueue(new Queue(QUEUE_NAME));
    }

    @Override
    @RabbitListener(queues = QUEUE_NAME)
    public String handle(String message) {
        SaveFinancialTransaction transactionToSave = gson.fromJson(message, SaveFinancialTransaction.class);
        FinancialTransaction transaction = financialTransactionService.save(transactionToSave);
        return gson.toJson(transaction);
    }
}
