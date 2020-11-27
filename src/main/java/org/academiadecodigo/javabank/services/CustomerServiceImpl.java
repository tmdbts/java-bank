package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.model.account.Account;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An {@link CustomerService} implementation
 */
public class CustomerServiceImpl implements CustomerService {

    private Map<Integer, Customer> customerMap = new HashMap<>();

    /**
     * Gets the next account id
     *
     * @return the next id
     */
    private Integer getNextId() {
        return customerMap.isEmpty() ? 1 : Collections.max(customerMap.keySet()) + 1;
    }

    /**
     * @see CustomerService#get(Integer)
     */
    @Override
    public Customer get(Integer id) {
        return customerMap.get(id);
    }

    /**
     * @see CustomerService#list()
     */
    @Override
    public List<Customer> list() {
        return new ArrayList<>(customerMap.values());
    }

    /**
     * @see CustomerService#listCustomerAccountIds(Integer)
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        List<Account> accountList = customerMap.get(id).getAccounts();

        return accountList.stream()
                .map(Model::getId)
                .collect(Collectors.toSet());
    }

    /**
     * @see CustomerService#getBalance(int)
     */
    @Override
    public double getBalance(int id) {

        List<Account> accounts = customerMap.get(id).getAccounts();

        return accounts.stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }

    /**
     * @see CustomerService#add(Customer)
     */
    @Override
    public void add(Customer customer) {

        if (customer.getId() == null) {
            customer.setId(getNextId());
        }

        customerMap.put(customer.getId(), customer);
    }
}
