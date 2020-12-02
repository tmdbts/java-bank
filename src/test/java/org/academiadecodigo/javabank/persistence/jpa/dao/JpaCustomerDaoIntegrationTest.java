package org.academiadecodigo.javabank.persistence.jpa.dao;

import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.persistence.model.account.Account;
import org.academiadecodigo.javabank.persistence.model.account.CheckingAccount;
import org.academiadecodigo.javabank.persistence.model.account.SavingsAccount;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaCustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaIntegrationTestHelper;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.*;

public class JpaCustomerDaoIntegrationTest extends JpaIntegrationTestHelper {

    private final static Integer INVALID_ID = 9999;
    private final static double DOUBLE_DELTA = 0.1;

    private JpaCustomerDao customerDao;

    @Before
    public void setup() {
        customerDao = new JpaCustomerDao();
        customerDao.setSm(sm);
    }

    @Test
    public void testFindById() {

        // setup
        int id = 1;

        // exercise
        Customer customer = customerDao.findById(id);

        // verify
        assertNotNull("Customer is null", customer);
        assertEquals("Customer id is wrong", id, customer.getId().intValue());
        assertEquals("Customer first name is wrong", "Rui", customer.getFirstName());
        assertEquals("Customer last name is wrong", "Ferrão", customer.getLastName());
        assertEquals("Customer email is wrong", "mail@gmail.com", customer.getEmail());
        assertEquals("Customer phone is wrong", "777888", customer.getPhone());

    }

    @Test()
    public void testFindByIdInvalid() {

        // exercise
        Customer customer = customerDao.findById(INVALID_ID);

        // verify
        assertNull("invalid customer should not be found", customer);

    }

    @Test
    public void testFindAll() {

        // exercise
        List<Customer> customers = customerDao.findAll();

        // verify
        assertNotNull("customers are null", customers);
        assertEquals("Number of customer is wrong", 4, customers.size());

    }

    @Test
    public void testFindAllFail() {

        // setup
        tx.beginWrite();
        Query query = sm.getCurrentSession().createQuery("delete from Account ");
        query.executeUpdate();
        query = sm.getCurrentSession().createQuery("delete from Recipient");
        query.executeUpdate();
        query = sm.getCurrentSession().createQuery("delete from Customer");
        query.executeUpdate();
        tx.commit();

        // exercise
        List<Customer> customers = customerDao.findAll();

        // verify
        assertNotNull("Customers are null", customers);
        assertEquals("Number of customers is wrong", 0, customers.size());

    }

    @Test
    public void testAddCustomerNoAccounts() {

        // setup
        String firstName = "new first name";
        String lastName = "new last name";
        String email = "new email";
        String phone = "999666";
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        newCustomer.setPhone(phone);

        // exercise
        tx.beginWrite();
        Customer addedCustomer = customerDao.saveOrUpdate(newCustomer);
        tx.commit();

        // verify
        assertNotNull("customer not added", addedCustomer);
        Customer customer = sm.getCurrentSession().find(Customer.class, addedCustomer.getId());
        assertNotNull("Customer not found", customer);
        assertEquals(newCustomer.getFirstName(), customer.getFirstName());
        assertEquals(newCustomer.getLastName(), customer.getLastName());
        assertEquals(newCustomer.getEmail(), customer.getEmail());
        assertEquals(newCustomer.getPhone(), customer.getPhone());

    }

    @Test
    public void testAddCustomerWithAccounts() {

        // setup
        double caBalance = 100;
        double saBalance = 101;
        Account ca = new CheckingAccount();
        Account sa = new SavingsAccount();
        ca.credit(caBalance);
        sa.credit(saBalance);

        Customer newCustomer = new Customer();
        newCustomer.addAccount(ca);
        newCustomer.addAccount(sa);

        // exercise
        tx.beginWrite();
        Customer addedCustomer = customerDao.saveOrUpdate(newCustomer);
        tx.commit();

        // verify
        assertNotNull("customer not added", addedCustomer);
        Customer customer = sm.getCurrentSession().find(Customer.class, addedCustomer.getId());
        assertNotNull("customer not found", addedCustomer);
        assertNotNull("customer accounts not found", customer.getAccounts());
        assertEquals("customer number of accounts wrong", newCustomer.getAccounts().size(), customer.getAccounts().size());
        assertEquals("first account balance is wrong", caBalance, customer.getAccounts().get(0).getBalance(), DOUBLE_DELTA);
        assertEquals("second account balance is wrong", saBalance, customer.getAccounts().get(1).getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testUpdateCustomer() {

        // setup
        int id = 1;
        String firstName = "updated customer";
        Customer customer = sm.getCurrentSession().find(Customer.class, id);
        customer.setFirstName(firstName);

        // exercise
        tx.beginWrite();
        customerDao.saveOrUpdate(customer);
        tx.commit();

        // verify
        customer = sm.getCurrentSession().find(Customer.class, id);
        assertEquals("customer first name is wrong", firstName, customer.getFirstName());

    }

    @Test
    public void testUpdateCustomerWithAccounts() {

        // setup
        int id = 1;
        String firstName = "updated customer";
        Customer existingCustomer = sm.getCurrentSession().find(Customer.class, id);
        existingCustomer.setFirstName(firstName);
        existingCustomer.getAccounts().get(0).canCredit(100);

        // exercise
        tx.beginWrite();
        customerDao.saveOrUpdate(existingCustomer);
        tx.commit();

        // verify
        Customer customer = sm.getCurrentSession().find(Customer.class, id);
        assertEquals("customer first name is wrong", firstName, customer.getFirstName());
        assertEquals("number of accounts is wrong", 2, customer.getAccounts().size());
        assertEquals("account balance is wrong", 100, customer.getAccounts().get(0).getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testUpdatedCustomerOrphanAccountDelete() {

        // setup
        int id = 1;
        String firstName = "updated customer";
        Customer existingCustomer = sm.getCurrentSession().find(Customer.class, id);
        existingCustomer.setFirstName(firstName);
        existingCustomer.removeAccount(existingCustomer.getAccounts().get(1));

        // exercise
        tx.beginWrite();
        customerDao.saveOrUpdate(existingCustomer);
        tx.commit();

        // verify
        Customer customer = sm.getCurrentSession().find(Customer.class, id);
        assertEquals("customer first name is wrong", firstName, customer.getFirstName());
        assertEquals("number of accounts is wrong", 1, customer.getAccounts().size());
        assertEquals("account balance is wrong", 100, customer.getAccounts().get(0).getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testDeleteCustomer() {

        // setup
        int id = 1;

        // exercise
        tx.beginWrite();
        customerDao.delete(id);
        tx.commit();

        // verify
        Customer customer = sm.getCurrentSession().find(Customer.class, id);
        assertNull("should be null", customer);
    }

    @Test
    public void testDeleteCustomerNoAccounts() {

        // setup
        int id = 4;

        // exercise
        tx.beginWrite();
        customerDao.delete(id);
        tx.commit();

        // verify
        Customer customer = sm.getCurrentSession().find(Customer.class, id);
        assertNull("should be null", customer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteInvalid() {

        // exercise
        tx.beginWrite();
        customerDao.delete(INVALID_ID);
        tx.commit();
    }
}
