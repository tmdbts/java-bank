package org.academiadecodigo.javabank.controller.application.operations;

import org.academiadecodigo.javabank.controller.application.BankApplication;
import org.academiadecodigo.javabank.model.domain.Customer;

public abstract class AbstractBankOperation implements Operation {

    protected BankApplication bankApplication;
    protected Customer customer;

    /**
     * Initializes a new {@code AbstractBankOperation} given a bank application
     *
     * @param bankApplication the bank application
     */
    public AbstractBankOperation(BankApplication bankApplication) {
        this.bankApplication = bankApplication;
        customer = bankApplication.getBank().getCustomer(bankApplication.getAccessingCustomerId());
    }
}
