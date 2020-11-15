package org.academiadecodigo.javabank.controller.application.operations.transaction;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.controller.application.BankApplication;
import org.academiadecodigo.javabank.controller.application.Messages;
import org.academiadecodigo.javabank.controller.application.operations.AbstractBankOperation;
import org.academiadecodigo.javabank.managers.AccountManager;

public abstract class AbstractAccountTransactionOperation extends AbstractBankOperation {

    protected AccountManager accountManager;
    private Prompt prompt;

    /**
     * Initializes a new {@code AbstractBankOperation} given a bank application
     *
     * @param bankApplication the bank application
     */
    public AbstractAccountTransactionOperation(BankApplication bankApplication) {
        super(bankApplication);
        prompt = bankApplication.getPrompt();
        accountManager = bankApplication.getBank().getAccountManager();
    }

    @Override
    public void execute() {

        if (!hasAccounts()) {
            System.out.println("\n" + Messages.ERROR_NO_ACCOUNT);
            return;
        }

        System.out.println("\n" + Messages.OPEN_ACCOUNTS + buildAccountList());
    }

    protected boolean hasAccounts() {
        return customer.getAccountIds().size() > 0;
    }

    private String buildAccountList() {

        StringBuilder builder = new StringBuilder();

        for (Integer id : customer.getAccountIds()) {
            builder.append(id);
            builder.append(" ");
        }

        return builder.toString();
    }

    protected int scanAccount() {
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(customer.getAccountIds());
        scanner.setMessage(Messages.CHOOSE_ACCOUNT);
        scanner.setError(Messages.ERROR_INVALID_ACCOUNT);

        return prompt.getUserInput(scanner);
    }

    protected double scanAmount() {
        DoubleInputScanner scanner = new DoubleInputScanner();
        scanner.setMessage(Messages.CHOOSE_AMOUNT);
        scanner.setError(Messages.ERROR_INVALID_AMOUNT);

        return prompt.getUserInput(scanner);
    }
}
