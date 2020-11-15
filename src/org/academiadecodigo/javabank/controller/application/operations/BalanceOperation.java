package org.academiadecodigo.javabank.controller.application.operations;

import org.academiadecodigo.javabank.controller.application.BankApplication;
import org.academiadecodigo.javabank.views.operations.BalanceOperationView;

import java.text.DecimalFormat;

public class BalanceOperation extends AbstractBankOperation {
    private DecimalFormat df = new DecimalFormat("#.##");

    /**
     * Initializes a new {@code AbstractBankOperation} given a bank application
     *
     * @param bankApplication the bank application
     */
    public BalanceOperation(BankApplication bankApplication) {
        super(bankApplication);
    }

    @Override
    public void execute() {
        BalanceOperationView balanceOperationView = new BalanceOperationView();
        balanceOperationView.printBalance(df, customer);
    }
}
