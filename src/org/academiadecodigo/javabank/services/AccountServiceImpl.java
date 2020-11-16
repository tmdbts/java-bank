package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.Account;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * An {@link AccountService} implementation
 */
public class AccountServiceImpl implements AccountService {

    private Map<Integer, Account> accountMap = new HashMap<>();

    /**
     * Gets the next account id
     *
     * @return the next id
     */
    private Integer getNextId() {
        return accountMap.isEmpty() ? 1 : Collections.max(accountMap.keySet()) + 1;
    }

    /**
     * @see AccountService#add(Account)
     */
    public void add(Account account) {

        if (account.getId() == null) {
            account.setId(getNextId());
        }

        accountMap.put(account.getId(), account);
    }

    /**
     * @see AccountService#deposit(int, double)
     */
    public void deposit(int id, double amount) {
        accountMap.get(id).credit(amount);
    }

    /**
     * @see AccountService#withdraw(int, double)
     */
    public void withdraw(int id, double amount) {

        Account account = accountMap.get(id);

        if (!account.canWithdraw()) {
            return;
        }

        accountMap.get(id).debit(amount);
    }

    /**
     * @see AccountService#transfer(int, int, double)
     */
    public void transfer(int srcId, int dstId, double amount) {

        Account srcAccount = accountMap.get(srcId);
        Account dstAccount = accountMap.get(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }
    }
}
