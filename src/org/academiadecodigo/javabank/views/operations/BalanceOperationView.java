package org.academiadecodigo.javabank.views.operations;

import org.academiadecodigo.javabank.application.Messages;
import org.academiadecodigo.javabank.model.domain.Customer;
import org.academiadecodigo.javabank.model.domain.account.Account;

import java.text.DecimalFormat;
import java.util.Set;

public class BalanceOperationView {

    public void printBalance(DecimalFormat df, Customer customer) {
        System.out.println("\n" + customer.getName() + Messages.BALANCE_MESSAGE + "\n");

        Set<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.BALANCE_TOTAL_MESSAGE + df.format(customer.getBalance()));
    }
}
