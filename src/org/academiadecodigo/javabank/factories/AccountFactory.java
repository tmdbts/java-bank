package org.academiadecodigo.javabank.factories;

<<<<<<< HEAD
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.model.account.CheckingAccount;
import org.academiadecodigo.javabank.model.account.SavingsAccount;
=======
import org.academiadecodigo.javabank.model.domain.account.Account;
import org.academiadecodigo.javabank.model.domain.account.AccountType;
import org.academiadecodigo.javabank.model.domain.account.CheckingAccount;
import org.academiadecodigo.javabank.model.domain.account.SavingsAccount;
>>>>>>> ac7226f0c7d408d4bbc4d9fafd9620d1f0706878

/**
 * A factory for creating accounts of different types
 */
public class AccountFactory {

    private int nextAccountId = 1;

    /**
     * Gets the next account id
     *
     * @return the next account id
     */
    private int getNextId() {
        return nextAccountId++;
    }

    /**
     * Creates a new {@link Account}
     *
     * @param accountType the account type
     * @return the new account
     */
    public Account createAccount(AccountType accountType) {

        Account newAccount;
        switch (accountType) {
            case CHECKING:
                newAccount = new CheckingAccount(getNextId());
                break;
            case SAVINGS:
                newAccount = new SavingsAccount(getNextId());
                break;
            default:
                newAccount = null;

        }

        return newAccount;
    }
}
