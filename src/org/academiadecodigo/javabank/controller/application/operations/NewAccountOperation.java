package org.academiadecodigo.javabank.controller.application.operations;

import org.academiadecodigo.javabank.controller.application.BankApplication;
import org.academiadecodigo.javabank.model.domain.account.AccountType;
import org.academiadecodigo.javabank.views.operations.NewAccountOperationView;

public class NewAccountOperation extends AbstractBankOperation {

    /**
     * Initializes a new {@code AbstractBankOperation} given a bank application
     *
     * @param bankApplication the bank application
     */
    public NewAccountOperation(BankApplication bankApplication) {
        super(bankApplication);
    }

    @Override
    public void execute() {
        int accountId = customer.openAccount(AccountType.CHECKING);

        NewAccountOperationView newAccountOperationView = new NewAccountOperationView();
        newAccountOperationView.showSuccessMessage(accountId, customer);
    }
}
