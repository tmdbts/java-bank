package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

public class App {

    public static void main(String[] args) {


        Bank bank = new Bank(new AccountManager());
        Customer customer = new Customer();

        bank.addCustomer(customer);
        customer.openAccount(AccountType.CHECKING);

        Prompt prompt = new Prompt(System.in, System.out);

        StringInputScanner accIdInput = new StringInputScanner();

        String accId = prompt.getUserInput(accIdInput);
    }
}
