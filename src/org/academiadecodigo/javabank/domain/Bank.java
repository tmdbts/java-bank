package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.HashMap;
import java.util.Map;

/**
 * The bank entity
 */
public class Bank {

    private AccountManager accountManager;
    private Map<Integer, Customer> customers = new HashMap<>();

    /**
     * Creates a new instance of Bank and initializes it with the given account manager
     *
     * @param accountManager the account manager
     */
    public Bank(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Adds a new customer to the bank
     *
     * @param customer the new bank customer
     * @see Customer#setAccountManager(AccountManager)
     */
    public void addCustomer(Customer customer) {
        int customerNumber = customers.size() + 1;
        customers.put(customerNumber, customer);
        customer.setAccountManager(accountManager);
        customer.setCustomerNumber(customerNumber);
    }

    /**
     * Gets the total balance of the bank
     *
     * @return the bank total balance
     */
    public double getBalance() {

        double balance = 0;

        for (Customer customer : customers.values()) {
            balance += customer.getBalance();
        }

        return balance;
    }

    /**
     * Gets all the customers of the bank
     *
     * @return all the customers
     */
    public Map<Integer, Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomerByNumber(Integer customerNumber) {
        for (Customer customer : customers.values()) {
            if (customer.getCustomerNumber() == customerNumber) {
                return customer;
            }
        }

        return null;
    }
}
