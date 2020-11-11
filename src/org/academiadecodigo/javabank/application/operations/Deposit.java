package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.managers.AccountManager;

public class Deposit implements Operation {

    @Override
    public void execute(Customer customer) {

        Prompt prompt = new Prompt(System.in, System.out);
        IntegerInputScanner intInputScanner = new IntegerInputScanner();

        AccountManager accountManager = customer.getAccountManager();

        intInputScanner.setMessage("Select the account id to deposit: ");
        int id = prompt.getUserInput(intInputScanner);

        intInputScanner.setMessage("Quantity to deposit: ");
        int quantityToDeposit = prompt.getUserInput(intInputScanner);

        accountManager.deposit(id, quantityToDeposit);
    }
}
