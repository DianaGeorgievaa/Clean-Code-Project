package Accounts;

import Exceptions.NegativeAmountException;
import Exceptions.NotEnoughMoneyException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrivilegeAccountTest {
    private static final String IBAN = "AFRT8734570912TRCFOPWR";
    private static final String OWNER_ID = "123";
    private static final double START_AMOUNT = 200;
    private static final double OVERDRAFT = 700;

    private static final double WITHDRAW_WITH_OVERDRAFT_AMOUNT = 300;
    private static final double WITHDRAW_AMOUNT = 20;
    private static final double NEGATIVE_AMOUNT = -200;
    private static final double BIGGER_AMOUNT = 18824;

    private Account privilegeAccount;

    @Before
    public void setUp() {
        this.privilegeAccount = new PrivilegeAccount(IBAN, OWNER_ID, START_AMOUNT, OVERDRAFT);
    }

    @Test
    public void testPositiveAmountWithdrawFromPrivilegeAccount() {
        this.privilegeAccount.withdraw(WITHDRAW_AMOUNT);
        assertEquals(START_AMOUNT - WITHDRAW_AMOUNT, this.privilegeAccount.getBalance(), 0.0);
    }

    @Test(expected = NegativeAmountException.class)
    public void testNegativeAmountWithdrawFromPrivilegeAccount() {
        this.privilegeAccount.withdraw(NEGATIVE_AMOUNT);
    }

    @Test
    public void testPositiveAmountWithdrawWithOverdraft() {
        this.privilegeAccount.withdraw(WITHDRAW_WITH_OVERDRAFT_AMOUNT);
        assertEquals(START_AMOUNT - WITHDRAW_WITH_OVERDRAFT_AMOUNT, this.privilegeAccount.getBalance(), 0.0);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void testBiggerAmountWithdrawFromPrivilegeAccountThrowsException() {
        this.privilegeAccount.withdraw(BIGGER_AMOUNT);
    }
}