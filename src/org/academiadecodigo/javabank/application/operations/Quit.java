package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.javabank.domain.Customer;

public class Quit implements Operation {
    
    @Override
    public void execute(Customer customer) {
        System.exit(0);
    }
}
