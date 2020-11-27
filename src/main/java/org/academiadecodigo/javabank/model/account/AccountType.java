package org.academiadecodigo.javabank.model.account;

import javax.persistence.ManyToOne;

/**
 * The possible {@link Account} types
 */
public enum AccountType {

    /**
     * @see CheckingAccount
     */
    CHECKING,

    /**
     * @see SavingsAccount
     */
    SAVINGS
}
