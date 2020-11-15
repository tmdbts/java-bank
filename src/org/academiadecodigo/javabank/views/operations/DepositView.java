package org.academiadecodigo.javabank.views.operations;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.controller.application.Messages;
import org.academiadecodigo.javabank.model.domain.Customer;

public class DepositView {

    Prompt prompt = new Prompt(System.in, System.out);

    public int scanAccount(Customer customer) {
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(customer.getAccountIds());
        scanner.setMessage(Messages.CHOOSE_ACCOUNT);
        scanner.setError(Messages.ERROR_INVALID_ACCOUNT);

        return prompt.getUserInput(scanner);
    }

    public double scanAmount() {

        DoubleInputScanner scanner = new DoubleInputScanner();
        scanner.setMessage(Messages.CHOOSE_AMOUNT);
        scanner.setError(Messages.ERROR_INVALID_AMOUNT);

        return prompt.getUserInput(scanner);
    }
}
