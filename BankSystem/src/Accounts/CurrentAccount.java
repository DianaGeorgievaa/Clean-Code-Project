package Accounts;

import Exceptions.NotEnoughMoneyException;

public class CurrentAccount extends Account {

    public CurrentAccount(String iban, String ownerId, double amount) {
        super(iban, ownerId, amount);
    }

    /**
     * Add specific amount to account
     *
     * @param amount
     */
    @Override
    public void deposit(double amount) {
        super.addAmount(amount);
    }

    /**
     * Remove specific amount from account
     *
     * @param amount
     * @throws NotEnoughMoneyException
     */
    @Override
    public void withdraw(double amount) {
        double currentAmount = super.getBalance();
        if (currentAmount < amount) {
            throw new NotEnoughMoneyException("Not enough money in the account!");
        }
        super.removeAmount(amount);
    }

    @Override
    public void displayAccountInformation() {
        super.displayAccountInformation();
    }
}