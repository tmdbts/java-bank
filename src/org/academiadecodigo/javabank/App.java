package org.academiadecodigo.javabank;

<<<<<<< HEAD
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.model.Bank;
=======
import org.academiadecodigo.javabank.controller.application.BankApplication;
import org.academiadecodigo.javabank.model.domain.Bank;
import org.academiadecodigo.javabank.model.domain.Customer;
import org.academiadecodigo.javabank.managers.AccountManager;
>>>>>>> ac7226f0c7d408d4bbc4d9fafd9620d1f0706878

public class App {

    private Bank bank;

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        Bootstrap bootstrap = new Bootstrap();
        bank = bootstrap.generateTestData();

        LoginController loginController = bootstrap.wireObjects(bank);

        // start application
        loginController.init();
    }
}
