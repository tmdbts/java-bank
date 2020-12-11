package org.academiadecodigo.javabank.exceptions;

/**
 * Thrown to indicate that the customer was not found
 */
public class CustomerNotFoundException extends NotFoundException {

    /**
     * @see NotFoundException#NotFoundException(String)
     */
    public CustomerNotFoundException() {
        super("Customer not found");
    }
}
