package com.modus.create.gateway.controller;

import com.modus.create.financial.transactions.command.SaveFinancialTransaction;
import com.modus.create.gateway.command.MakeFinancialTransaction;
import com.modus.create.gateway.message.GenericMessage;
import com.modus.create.gateway.security.AuthContext;
import com.modus.create.gateway.service.FinancialTransactionService;
import com.modus.create.gateway.translator.FinancialTransactionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class FinancialTransactionController {

    private AuthContext authContext;
    private FinancialTransactionService financialTransactionService;
    private FinancialTransactionTranslator financialTransactionTranslator;

    @Autowired
    public FinancialTransactionController(AuthContext authContext, FinancialTransactionService financialTransactionService, FinancialTransactionTranslator financialTransactionTranslator) {
        this.authContext = authContext;
        this.financialTransactionService = financialTransactionService;
        this.financialTransactionTranslator = financialTransactionTranslator;
    }

    @RequestMapping(method = POST, value = "/transaction")
    public GenericMessage makeTransaction(@RequestBody MakeFinancialTransaction makeFinancialTransaction) {
        SaveFinancialTransaction saveFinancialTransaction = financialTransactionTranslator.translate(makeFinancialTransaction, authContext.getCurrentUserId());
        financialTransactionService.makeTransaction(saveFinancialTransaction);

        return new GenericMessage("Transaction taken and will be processed");
    }
}
