package org.academiadecodigo.javabank.services.mock;

import org.academiadecodigo.javabank.exceptions.AccountNotFoundException;
import org.academiadecodigo.javabank.persistence.model.account.Account;
import org.academiadecodigo.javabank.persistence.model.account.AccountType;
import org.academiadecodigo.javabank.services.AccountService;

import java.util.Optional;

/**
 * A mock {@link AccountService} implementation
 */
public class MockAccountService extends AbstractMockService<Account> implements AccountService {

    /**
     * @see AccountService#get(Integer)
     */
    @Override
    public Account get(Integer id) {
        return modelMap.get(id);
    }

    /**
     * @see AccountService#deposit(Integer, double)
     */
    public void deposit(Integer id, double amount) throws AccountNotFoundException {

//        Account account = modelMap.get(id);
//
//        if (account == null) {
//            throw new AccountNotFoundException();
//        }
//
//        account.credit(amount);

        Optional.ofNullable(modelMap.get(id))
                .orElseThrow(AccountNotFoundException::new)
                .credit(amount);
    }

    /**
     * @see AccountService#withdraw(Integer, double)
     */
    public void withdraw(Integer id, double amount) throws AccountNotFoundException {

        Account account = Optional.ofNullable(modelMap.get(id))
                .orElseThrow(AccountNotFoundException::new);

        if (!account.canWithdraw()) {
            return;
        }

        modelMap.get(id).debit(amount);
    }

    /**
     * @see AccountService#transfer(Integer, Integer, double)
     */
    public void transfer(Integer srcId, Integer dstId, double amount) throws AccountNotFoundException {

        Account srcAccount = Optional.ofNullable(modelMap.get(srcId))
                .orElseThrow(AccountNotFoundException::new);

        Account dstAccount = Optional.ofNullable(modelMap.get(dstId))
                .orElseThrow(AccountNotFoundException::new);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }
    }
}
