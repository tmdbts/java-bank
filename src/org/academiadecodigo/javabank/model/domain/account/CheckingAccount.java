<<<<<<< HEAD:src/org/academiadecodigo/javabank/model/account/CheckingAccount.java
package org.academiadecodigo.javabank.model.account;
=======
package org.academiadecodigo.javabank.model.domain.account;
>>>>>>> ac7226f0c7d408d4bbc4d9fafd9620d1f0706878:src/org/academiadecodigo/javabank/model/domain/account/CheckingAccount.java

/**
 * A checking account with no restrictions
 *
 * @see Account
 * @see AccountType#CHECKING
 */
public class CheckingAccount extends AbstractAccount {

    /**
     * Creates a new {@code CheckingAccount} instance
     *
     * @see AbstractAccount#AbstractAccount(int)
     */
    public CheckingAccount(int id) {
        super(id);
    }

    /**
     * @see Account#getAccountType()
     */
    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
