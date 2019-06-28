package Accounts;

import Exceptions.InvalidIbanException;
import Exceptions.NegativeAmountException;

public abstract class Account {

    private static final int IBAN_LENGTH = 22;
    private String iban;
    private String ownerId;
    private double amount;

    public Account(String iban, String ownerId, double amount) {
        this.setIban(iban);
        this.setOwnerId(ownerId);
        this.setAmount(amount);
    }

    public String getIban() {
        return this.iban;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public double getBalance() {
        return this.amount;
    }

    public void setIban(String iban) {
        int lengthOfIban = iban.length();
        if (lengthOfIban != IBAN_LENGTH) {
            throw new InvalidIbanException("The Iban should be with 22 symbols!");
        }
        this.iban = iban;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setAmount(double amount) {
        this.validateAmount(amount);
        this.amount = amount;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public void displayAccountInformation() {
        System.out.println("Iban: " + this.getIban());
        System.out.println("Owner Id: " + this.getOwnerId());
        System.out.println("Current balance: " + this.getBalance());
    }

    /**
     * Checks for negative amount
     *
     * @param amount
     * @throws NegativeAmountException
     */
    protected void validateAmount(double amount) {
        if (amount < 0) {
            throw new NegativeAmountException("The amount can not be negative!");
        }
    }

    /**
     * Helper function for deposit operation
     *
     * @param amountToAdd
     * @see CurrentAccount#deposit
     */
    protected void addAmount(double amountToAdd) {
        this.validateAmount(amountToAdd);
        this.amount += amountToAdd;
        System.out.println("Successful operation!");
    }

    /**
     * Helper function for withdraw operation
     *
     * @param amountToRemove
     * @see CurrentAccount#withdraw(double)
     */
    protected void removeAmount(double amountToRemove) {
        this.validateAmount(amountToRemove);
        this.amount -= amountToRemove;
        System.out.println("Successful operation!");
    }
}