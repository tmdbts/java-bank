package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.exceptions.AccountNotFoundException;
import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.academiadecodigo.javabank.persistence.model.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * An {@link AccountService} implementation
 */
@Service
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    /**
     * Sets the account data access object
     *
     * @param accountDao the account DAO to set
     */
    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * @see AccountService#get(Integer)
     */
    @Override
    public Account get(Integer id) throws AccountNotFoundException {

        Account account = accountDao.findById(id);

        if (account == null) {
            throw new AccountNotFoundException();
        }

        return account;
    }

    /**
     * @see AccountService#deposit(Integer, double)
     */
    @Transactional
    @Override
    public void deposit(Integer id, double amount) throws AccountNotFoundException {

        Account account = Optional.ofNullable(accountDao.findById(id))
                .orElseThrow(AccountNotFoundException::new);

        account.credit(amount);

        accountDao.saveOrUpdate(account);
    }

    /**
     * @see AccountService#withdraw(Integer, double)
     */
    @Transactional
    @Override
    public void withdraw(Integer id, double amount) throws AccountNotFoundException {

        Account account = Optional.ofNullable(accountDao.findById(id))
                .orElseThrow(AccountNotFoundException::new);

        account.debit(amount);

        accountDao.saveOrUpdate(account);
    }

    /**
     * @see AccountService#transfer(Integer, Integer, double)
     */
    @Transactional
    @Override
    public void transfer(Integer srcId, Integer dstId, double amount) throws AccountNotFoundException {

        Optional<Account> srcAccount = Optional.ofNullable(accountDao.findById(srcId));
        Optional<Account> dstAccount = Optional.ofNullable(accountDao.findById(dstId));

        srcAccount.orElseThrow(AccountNotFoundException::new);
        dstAccount.orElseThrow(AccountNotFoundException::new);

        // make sure transaction can be performed
        if (srcAccount.get().canDebit(amount) && dstAccount.get().canCredit(amount)) {
            srcAccount.get().debit(amount);
            dstAccount.get().credit(amount);
        }

        accountDao.saveOrUpdate(srcAccount.get());
        accountDao.saveOrUpdate(dstAccount.get());
    }
}


