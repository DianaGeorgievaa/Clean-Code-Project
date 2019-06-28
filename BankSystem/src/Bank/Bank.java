package Bank;

import Accounts.Account;
import Managers.CustomerManager;
import Managers.CustomerManagerInterface;

public class Bank implements BankInterface {

    private String name;
    private String address;
    private CustomerManagerInterface customerManager;

    public Bank(String name, String address, CustomerManagerInterface customerManager) {
        this.setName(name);
        this.setAddress(address);
        this.customerManager = customerManager;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public void displayBankInformation() {
        System.out.println("Bank information: ");
        System.out.println("Name of the bank: " + this.getName());
        System.out.println("Address of the bank: " + this.getAddress());
    }

    /**
     * If the customer exists, withdraw amount from his account with the specific Iban
     *
     * @param ownerId
     * @param iban
     * @param amount
     * @see Accounts.CurrentAccount#withdraw(double)
     */
    @Override
    public void withdrawFromAccount(String ownerId, String iban, double amount) {
        Account account = this.customerManager.getAccount(ownerId, iban);
        account.withdraw(amount);
    }

    /**
     * If the customer exists, deposit amount to his account with the specific Iban
     *
     * @param ownerId
     * @param iban
     * @param amount
     * @see Accounts.CurrentAccount#deposit(double)
     */
    @Override
    public void depositToAccount(String ownerId, String iban, double amount) {
        Account account = this.customerManager.getAccount(ownerId, iban);
        account.deposit(amount);
    }

    /**
     * Withdraw amount from the account sender and deposit it to the account receiver
     *
     * @param senderId
     * @param senderAccountIban
     * @param receiverId
     * @param receiverAccountIban
     * @param amount
     */
    @Override
    public void transfer(String senderId, String senderAccountIban, String receiverId, String receiverAccountIban, double amount) {
        Account sender = this.customerManager.getAccount(senderId, senderAccountIban);
        Account receiver = this.customerManager.getAccount(receiverId, receiverAccountIban);

        sender.withdraw(amount);
        receiver.deposit(amount);
    }
}