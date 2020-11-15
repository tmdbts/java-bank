package org.academiadecodigo.javabank.controller.application.operations.transaction;

import org.academiadecodigo.javabank.controller.application.BankApplication;
import org.academiadecodigo.javabank.views.operations.DepositView;

public class DepositOperation extends AbstractAccountTransactionOperation {

    DepositView depositView = new DepositView();

    /**
     * Initializes a new {@code AbstractBankOperation} given a bank application
     *
     * @param bankApplication the bank application
     */
    public DepositOperation(BankApplication bankApplication) {
        super(bankApplication);
    }

    @Override
    public void execute() {

        super.execute();

        if (!hasAccounts()) {
            return;
        }

        Integer accountId = depositView.scanAccount(customer);
        Double amount = depositView.scanAmount();

        if (customer.getAccountIds().contains(accountId)) {
            accountManager.deposit(accountId, amount);
        }
    }
}
