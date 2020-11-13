package org.academiadecodigo.javabank.controller.application.operations.transaction;

import org.academiadecodigo.javabank.controller.application.BankApplication;
import org.academiadecodigo.javabank.controller.application.operations.AbstractBankOperation;

public class WithdrawOperation extends AbstractBankOperation {

    /**
     * Initializes a new {@code AbstractBankOperation} given a bank application
     *
     * @param bankApplication the bank application
     */
    public WithdrawOperation(BankApplication bankApplication) {
        super(bankApplication);
    }

    @Override
    public void execute() {
        
    }
}
