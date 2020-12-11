package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.exceptions.AccountNotFoundException;
import org.academiadecodigo.javabank.persistence.model.account.Account;

/**
 * Common interface for account services, provides methods to manage accounts and perform account transactions
 */
public interface AccountService {

    /**
     * Gets the account with the given id
     *
     * @param id the account id
     * @return the account
     * @throws AccountNotFoundException if the account doesn't exist
     */
    Account get(Integer id) throws AccountNotFoundException;

    /**
     * Perform an {@link Account} deposit
     *
     * @param id     the id of the account
     * @param amount the amount to deposit
     * @throws AccountNotFoundException if the account doesn't exist
     */
    void deposit(Integer id, double amount) throws AccountNotFoundException;

    /**
     * Perform an {@link Account} withdrawal
     *
     * @param id     the id of the account
     * @param amount the amount to withdraw
     * @throws AccountNotFoundException if the account doesn't exist
     */
    void withdraw(Integer id, double amount) throws AccountNotFoundException;

    /**
     * Performs a transfer between two {@link Account} if possible
     *
     * @param srcId  the source account id
     * @param dstId  the destination account id
     * @param amount the amount to transfer
     * @throws AccountNotFoundException if the source or destination account doesn't exist
     */
    void transfer(Integer srcId, Integer dstId, double amount) throws AccountNotFoundException;
}
