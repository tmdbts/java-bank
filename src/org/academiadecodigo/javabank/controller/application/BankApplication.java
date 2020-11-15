package org.academiadecodigo.javabank.controller.application;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.controller.application.operations.BalanceOperation;
import org.academiadecodigo.javabank.controller.application.operations.NewAccountOperation;
import org.academiadecodigo.javabank.controller.application.operations.Operation;
import org.academiadecodigo.javabank.controller.application.operations.transaction.DepositOperation;
import org.academiadecodigo.javabank.controller.application.operations.transaction.WithdrawOperation;
import org.academiadecodigo.javabank.model.domain.Bank;
import org.academiadecodigo.javabank.views.LoginView;
import org.academiadecodigo.javabank.views.MenuView;

import java.util.HashMap;
import java.util.Map;

public class BankApplication {

    private Prompt prompt;
    private MenuInputScanner mainMenu;
    private Map<Integer, Operation> operationsMap;

    private Bank bank;
    private int accessingCustomerId;

    private LoginView loginView = new LoginView();
    private MenuView menuView = new MenuView();

    /**
     * Creates a new instance of a {@code BankApplication}, initializes it with the given {@link Bank}
     *
     * @param bank the bank instance
     */
    public BankApplication(Bank bank) {
        this.bank = bank;
        this.prompt = new Prompt(System.in, System.out);
    }

    public void start() {

        mainMenu = buildMainMenu();

        accessingCustomerId = loginView.scanCustomerId(bank, prompt);
        operationsMap = buildOperationsMap();
        menuView.menuLoop(mainMenu, operationsMap, prompt);
    }

    private MenuInputScanner buildMainMenu() {

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        return mainMenu;
    }

    private Map<Integer, Operation> buildOperationsMap() {

        Map<Integer, Operation> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), new BalanceOperation(this));
        map.put(UserOptions.DEPOSIT.getOption(), new DepositOperation(this));
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawOperation(this));
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountOperation(this));

        return map;
    }

    public Bank getBank() {
        return bank;
    }

    public int getAccessingCustomerId() {
        return accessingCustomerId;
    }

    public Prompt getPrompt() {
        return prompt;
    }
}