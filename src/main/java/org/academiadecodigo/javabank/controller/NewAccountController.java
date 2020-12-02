package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.persistence.model.account.Account;
import org.academiadecodigo.javabank.persistence.model.account.AccountType;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.view.NewAccountView;

/**
 * The {@link NewAccountView} controller
 */
public class NewAccountController extends AbstractController {

    private Integer newAccountId;
    private AccountFactory accountFactory;
    private AccountService accountService;

    /**
     * Gets the new account id
     *
     * @return the new account id
     */
    public Integer getNewAccountId() {
        return newAccountId;
    }

    /**
     * Sets the account factory
     *
     * @param accountFactory the account factory to set
     */
    public void setAccountFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    /**
     * Sets the account service
     *
     * @param accountService the account service to set
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Creates a new {@link Account}
     *
     * @see Controller#init()
     * @see AccountFactory#createAccount(AccountType)
     */
    @Override
    public void init() {
        newAccountId = createAccount();
        super.init();
    }

    private int createAccount() {

        Account newAccount = accountFactory.createAccount(AccountType.CHECKING);
        authService.getAccessingCustomer().addAccount(newAccount);
        return accountService.add(newAccount);
    }
}
