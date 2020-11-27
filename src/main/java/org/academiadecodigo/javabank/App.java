package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.services.AccountServiceImpl;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.CustomerServiceImpl;
import org.academiadecodigo.javabank.services.DbCustomerServiceImpl;
import org.hibernate.bytecode.enhance.internal.javassist.EntityEnhancer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(new AccountServiceImpl());
        bootstrap.setCustomerService(new DbCustomerServiceImpl());
        bootstrap.loadCustomers();

        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();

        emf.close();
    }
}
