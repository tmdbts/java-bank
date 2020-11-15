package org.academiadecodigo.javabank.views.operations;

import org.academiadecodigo.javabank.application.Messages;
import org.academiadecodigo.javabank.model.domain.Customer;

public class NewAccountOperationView {

    public void showSuccessMessage(int accountId, Customer customer) {
        System.out.println("\n" + Messages.CREATED_ACCOUNT + customer.getName() + " : " + accountId);
    }
}
