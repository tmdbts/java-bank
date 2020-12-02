package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.persistence.model.Customer;

import java.util.Optional;

/**
 * An {@link AuthService} implementation
 */
public class AuthServiceImpl implements AuthService {

    private Integer accessingCustomerId;
    private CustomerService customerService;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * @see AuthService#authenticate(Integer)
     */
    @Override
    public boolean authenticate(Integer id) {

        Optional<Customer> customerOptional = Optional.ofNullable(customerService.get(id));

        customerOptional.ifPresent(customer -> accessingCustomerId = customer.getId());

        return customerOptional.isPresent();
    }

    /**
     * @see AuthService#getAccessingCustomer()
     */
    @Override
    public Customer getAccessingCustomer() {
        return customerService.get(accessingCustomerId);
    }
}
