package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.application.BankApplication;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;


public class App {

    public static void main(String[] args) {

        AccountManager accountManager = new AccountManager();

        Bank bank = new Bank(accountManager);

        init(bank);

        BankApplication bankApp = new BankApplication(bank, accountManager);
        bankApp.initMap();
        bankApp.menu();

    }

    private static void init(Bank bank) {

        Customer customer = new Customer();

        bank.addCustomer(customer);
        customer.openAccount(AccountType.CHECKING);
    }
}
