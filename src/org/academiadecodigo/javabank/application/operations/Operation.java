package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.javabank.domain.Customer;

public interface Operation {

    void execute(Customer customer);
}
