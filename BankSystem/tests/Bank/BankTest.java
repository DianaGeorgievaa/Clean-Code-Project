package Bank;

import Accounts.Account;
import Accounts.AccountType;
import Accounts.CurrentAccount;
import Customers.Customer;
import Managers.CustomerManager;
import Managers.CustomerManagerInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {
    private static final String SENDER_IBAN = "AFRT8734570912TRCFOPWR";
    private static final String SENDER_ID = "1950";
    private static final double SENDER_START_AMOUNT = 200;
    private static final String SENDER_NAME = "Petur Ivanov";
    private static final String SENDER_ADDRESS = "Sofia, Studentski grad, 6";

    private static final String RECEIVER_IBAN = "BFRT8732511912TRCFOPWR";
    private static final String RECEIVER_ID = "1840";
    private static final double RECEIVER_START_AMOUNT = 300;
    private static final String RECEIVER_NAME = "Ivan Ivanov";
    private static final String RECEIVER_ADDRESS = "Sofia, Studentski grad, 4";

    private static final AccountType accountType = AccountType.CURRENT;

    private static final double AMOUNT_TO_TRANSFER = 50;

    private static final String BANK_NAME = "DSK";
    private static final String BANK_ADDRESS = "Sofia, bul. Bulgaria, 10";

    private BankInterface bank;
    private CustomerManagerInterface customerManager;

    private void createCustomerManager() {
        Account accountSender = new CurrentAccount(SENDER_IBAN, SENDER_ID, SENDER_START_AMOUNT);
        Customer customerSender = new Customer(SENDER_ID, SENDER_NAME, SENDER_ADDRESS);
        this.customerManager.addCustomer(customerSender);
        this.customerManager.addAccount(accountSender, accountType);

        Account accountReceiver = new CurrentAccount(RECEIVER_IBAN, RECEIVER_ID, RECEIVER_START_AMOUNT);
        Customer customerReceiver = new Customer(RECEIVER_ID, RECEIVER_NAME, RECEIVER_ADDRESS);
        this.customerManager.addCustomer(customerReceiver);
        this.customerManager.addAccount(accountReceiver, accountType);
    }

    @Before
    public void setUp() {
        this.customerManager = new CustomerManager();
        this.createCustomerManager();
        this.bank = new Bank(BANK_NAME, BANK_ADDRESS, customerManager);
    }

    @Test
    public void testTransferFromAccountSenderToAccountReceiver() {
        this.bank.transfer(SENDER_ID, SENDER_IBAN, RECEIVER_ID, RECEIVER_IBAN, AMOUNT_TO_TRANSFER);
        Account sender = this.customerManager.getAccount(SENDER_ID, SENDER_IBAN);
        assertEquals(SENDER_START_AMOUNT - AMOUNT_TO_TRANSFER, sender.getBalance(), 0.0);
    }
}