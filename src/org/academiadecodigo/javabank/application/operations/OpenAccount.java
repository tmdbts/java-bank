package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;

import java.util.HashMap;
import java.util.Map;

public class OpenAccount implements Operation {

    private Map<Integer, AccountType> accTypes = new HashMap<>();
    private Prompt prompt = new Prompt(System.in, System.out);


    @Override
    public void execute(Customer customer) {

        addAccTypes();

        String[] menuOptions = {"Checking", "Savings"};
        MenuInputScanner menuScanner = new MenuInputScanner(menuOptions);

        menuScanner.setMessage("Chose your account type:");
        int accType = prompt.getUserInput(menuScanner);

        customer.openAccount(accTypes.get(accType));
    }


    private void addAccTypes() {
        accTypes.put(1, AccountType.CHECKING);
        accTypes.put(2, AccountType.SAVINGS);
    }
}
