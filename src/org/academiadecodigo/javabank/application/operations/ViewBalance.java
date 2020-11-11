package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;

import java.util.Map;

public class ViewBalance implements Operation {

    private Prompt prompt = new Prompt(System.in, System.out);

    @Override
    public void execute(Customer customer) {

        Map<Integer, Account> accounts = customer.getAccounts();

        String[] menuOptions = new String[accounts.size()];

        for (Account account : accounts.values()) {
            int i = 0;
            menuOptions[i] = account.getAccountType().toString();
        }

        MenuInputScanner menuScanner = new MenuInputScanner(menuOptions);

        menuScanner.setMessage("Chose your account:");
        int accId = prompt.getUserInput(menuScanner);

        System.out.println("\nYou have: " + customer.getBalance(accId) + " dinheiros.");
    }
}