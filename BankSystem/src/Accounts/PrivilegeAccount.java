package Accounts;

import Exceptions.NotEnoughMoneyException;

public class PrivilegeAccount extends CurrentAccount {

    private double overdraft;

    public PrivilegeAccount(String iban, String ownerId, double amount, double overdraft) {
        super(iban, ownerId, amount);
        this.setOverdraft(overdraft);
    }

    public double getOverdraft() {
        return this.overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    @Override
    public void withdraw(double amount) {
        // the amount that can be withdrawn is the current amount plus the overdraft
        double currentAllowedAmount = super.getBalance() + this.getOverdraft();
        if (currentAllowedAmount < amount) {
            throw new NotEnoughMoneyException("Not enough money in the account!");
        }
        super.removeAmount(amount);
    }

    @Override
    public void displayAccountInformation() {
        super.displayAccountInformation();
        System.out.println("Overdraft: " + this.getOverdraft());
    }
}