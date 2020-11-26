package org.academiadecodigo.javabank.model.account;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

/**
 * A generic account model entity to be used as a base for concrete types of accounts
 * @see Account
 */
@MappedSuperclass
public abstract class AbstractAccount extends AbstractModel implements Account {

    private double balance = 0;

    @Version
    private Integer version;

    @CreationTimestamp
    private Date creationTime;

    @UpdateTimestamp
    private Date lastUpdate;

    /**
     * @see Account#getBalance()
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @see Account#getAccountType()
     */
    public abstract AccountType getAccountType();

    /**
     * Gets the version on the DataBase
     *
     * @return version on the DataBase
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the version on the DataBase
     *
     * @param version the version on the DataBase
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * Gets the date of creation of the account
     *
     * @return the data of creation of the account
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * Sets the creation time of the account
     *
     * @param creationTime the creation time of the account
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * Gets the last time that the account was updated
     *
     * @return the last time that the account was updated
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the last time that the account was updated
     *
     * @param lastUpdate the last time that the account was updated
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Credits account if possible
     *
     * @param amount the amount to credit
     * @see Account#credit(double)
     */
    public void credit(double amount) {
        if (canCredit(amount)) {
            balance += amount;
        }
    }

    /**
     * Debits the account if possible
     *
     * @param amount the amount to debit
     * @see Account#canDebit(double)
     */
    public void debit(double amount) {
        if (canDebit(amount)) {
            balance -= amount;
        }
    }

    /**
     * @see Account#canCredit(double)
     */
    public boolean canCredit(double amount) {
        return amount > 0;
    }

    /**
     * @see Account#canDebit(double)
     */
    public boolean canDebit(double amount) {
        return amount > 0 && amount <= balance;
    }

    /**
     * @see Account#canWithdraw()
     */
    public boolean canWithdraw() {
        return true;
    }
}
