package org.academiadecodigo.javabank.application;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.application.operations.*;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.HashMap;
import java.util.Map;

public class BankApplication {

    Map<Integer, Operation> options = new HashMap<>();

    Bank bank;
    AccountManager accountManager;

    public BankApplication(Bank bank, AccountManager accountManager) {

        this.bank = bank;
        this.accountManager = accountManager;
    }

    public void initMap() {
        options.put(1, new ViewBalance());
        options.put(2, new Deposit());
        options.put(3, new Withdrawal());
        options.put(4, new OpenAccount());
        options.put(5, new Quit());
    }

    public void menu() {

        String[] menuOptions = {"View Balance", "Deposit", "Withdrawal", "Open account", "Exit"};

        Prompt prompt = new Prompt(System.in, System.out);

        StringInputScanner accIdInput = new StringInputScanner();
        accIdInput.setMessage("Welcome, enter your id number: ");


        Customer customer;

        while (true) {
            String accId = prompt.getUserInput(accIdInput);

            if (bank.getCustomerByNumber(Integer.parseInt(accId)) != null) {
                customer = bank.getCustomerByNumber(Integer.parseInt(accId));
                break;
            }

            accIdInput.setMessage("This user does not exist. Pleas insert a valid account number: ");
        }

        while (true) {

            MenuInputScanner menu = new MenuInputScanner(menuOptions);
            menu.setMessage("Chose an option.");

            int menuAnswer = prompt.getUserInput(menu);

            Operation selectedOperation = options.get(menuAnswer);
            selectedOperation.execute(customer);
        }
    }
}

