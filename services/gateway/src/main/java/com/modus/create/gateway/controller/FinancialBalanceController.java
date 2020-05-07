package com.modus.create.gateway.controller;

import com.modus.create.financial.ballance.query.RetrieveFinancialBalance;
import com.modus.create.financial.ballance.query.RetrievedFinancialBalance;
import com.modus.create.gateway.message.GenericMessage;
import com.modus.create.gateway.security.AuthContext;
import com.modus.create.gateway.service.FinancialBalanceRetrieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class FinancialBalanceController {

    private AuthContext authContext;
    private FinancialBalanceRetrieverService financialBalanceRetrieverService;

    @Autowired
    public FinancialBalanceController(AuthContext authContext, FinancialBalanceRetrieverService financialBalanceRetrieverService) {
        this.authContext = authContext;
        this.financialBalanceRetrieverService = financialBalanceRetrieverService;
    }


    @RequestMapping(method = GET, value = "/balance")
    public GenericMessage retrieveBalance() {
        RetrievedFinancialBalance retrievedFinancialBalance = financialBalanceRetrieverService.retrieveBalance(new RetrieveFinancialBalance(authContext.getCurrentUserId()));

        return new GenericMessage("Your monetary balance is: " + retrievedFinancialBalance.getMonetaryBalance());
    }
}
