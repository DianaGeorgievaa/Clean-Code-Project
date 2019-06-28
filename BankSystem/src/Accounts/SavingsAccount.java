package Accounts;

public class SavingsAccount extends CurrentAccount {

    private double interestRate;

    public SavingsAccount(String iban, String ownerId, double amount, double interestRate) {
        super(iban, ownerId, amount);
        this.setInterestRate(interestRate);
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void displayAccountInformation() {
        super.displayAccountInformation();
        System.out.println("Interest rate: " + this.getInterestRate());
    }
}