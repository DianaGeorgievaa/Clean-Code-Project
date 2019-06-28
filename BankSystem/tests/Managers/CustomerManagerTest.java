package Managers;

import Accounts.Account;
import Accounts.AccountType;
import Accounts.CurrentAccount;
import Customers.Customer;
import Customers.CustomerInterface;
import Exceptions.AccountAlreadyExistsException;
import Exceptions.CustomerAlreadyExistsException;
import Exceptions.CustomerNotFoundException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerManagerTest {
    private static final String IBAN = "AFRT8734570912TRCFOPWR";
    private static final String OWNER_ID = "1950";
    private static final double START_AMOUNT = 200;
    private static final String OWNER_NAME = "Petur Ivanov";
    private static final String OWNER_ADDRESS = "Sofia, Studentski grad, 6";
    private static final AccountType accountType = AccountType.CURRENT;

    private static final String INVALID_OWNER_ID = "3896";

    private CustomerManagerInterface customerManager;

    @Before
    public void setUp() {
        this.customerManager = new CustomerManager();
        Account account = new CurrentAccount(IBAN, OWNER_ID, START_AMOUNT);
        Customer customer = new Customer(OWNER_ID, OWNER_NAME, OWNER_ADDRESS);
        this.customerManager.addCustomer(customer);
        this.customerManager.addAccount(account, accountType);
    }

    @Test(expected = CustomerAlreadyExistsException.class)
    public void testAddAlreadyExistingCustomerThrowsException() {
        Customer customer = new Customer(OWNER_ID, OWNER_NAME, OWNER_ADDRESS);
        this.customerManager.addCustomer(customer);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void testDeleteNoExistingCustomerThrowsException() {
        this.customerManager.deleteCustomer(INVALID_OWNER_ID);
    }

    @Test(expected = AccountAlreadyExistsException.class)
    public void testAddAlreadyExistingAccountThrowsException() {
        Account account = new CurrentAccount(IBAN, OWNER_ID, START_AMOUNT);
        this.customerManager.addAccount(account, accountType);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void testAddAccountToNotExistingCustomerThrowsException() {
        Account account = new CurrentAccount(IBAN, INVALID_OWNER_ID, START_AMOUNT);
        this.customerManager.addAccount(account, accountType);
    }

    @Test
    public void testGetExistingAccountByCustomerId() {
        Account account = this.customerManager.getAccount(OWNER_ID, IBAN);
        assertEquals(IBAN, account.getIban());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void testGetAccountByNotExistingCustomerIdThrowsException() {
        Account account = this.customerManager.getAccount(INVALID_OWNER_ID, IBAN);
    }
}