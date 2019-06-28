package Menu;

import Accounts.Account;
import Accounts.AccountType;
import Accounts.CurrentAccount;
import Bank.Bank;
import Customers.Customer;
import Managers.CustomerManager;
import Managers.CustomerManagerInterface;

import java.util.Scanner;

public class Menu implements MenuInterface {

    private static final String BANK_NAME = "DSK";
    private static final String BANK_ADDRESS = "Sofia, bul. Bulgaria, 10";

    private Scanner scanner = new Scanner(System.in);

    private Bank bank;
    private CustomerManagerInterface customerManager;
    private boolean shouldQuit;

    public Menu() {
        this.customerManager = new CustomerManager();
        this.bank = new Bank(BANK_NAME, BANK_ADDRESS, this.customerManager);
        this.shouldQuit = false;
    }

    @Override
    public void printMenu() {
        System.out.println("*****Menu*****");
        this.printAdminOperations();
        this.printBankOperations();
        System.out.println("11 Quit");
    }

    @Override
    public void readInput() {
        while (!this.shouldQuit) {
            this.printMenu();
            System.out.println("Choose between 1 and 11");
            int command = scanner.nextInt();
            System.out.println();
            interpretCommand(command);
        }
    }

    private void printAdminOperations() {
        System.out.println("Admin operations 1-6 \n");
        System.out.println("1 List customers");
        System.out.println("2 Add new customer");
        System.out.println("3 Delete customer");
        System.out.println("4 List all accounts of specific customer");
        System.out.println("5 Add new account");
        System.out.println("6 Delete account \n");
    }

    private void printBankOperations() {
        System.out.println("Bank operations 7-10 \n");
        System.out.println("7 Withdraw from account");
        System.out.println("8 Deposit to account");
        System.out.println("9 Transfer");
        System.out.println("10 Display bank information \n");
    }

    private void interpretCommand(int command) {
        switch (command) {
            case 1:
                this.listCustomersCommand();
                break;
            case 2:
                this.addNewCustomerCommand();
                break;
            case 3:
                this.deleteCustomerCommand();
                break;
            case 4:
                this.listAllAccountsByCustomerIdCommand();
                break;
            case 5:
                this.addNewAccountCommand();
                break;
            case 6:
                this.deleteAccountCommand();
                break;
            case 7:
                this.withdrawFromAccountCommand();
                break;
            case 8:
                this.depositToAccountCommand();
                break;
            case 9:
                this.transferCommand();
                break;
            case 10:
                this.displayInformationForTheBankCommand();
                break;
            case 11:
                this.quitCommand();
                break;
            default:
                System.out.println("Invalid choice for command!");
                break;
        }
    }

    private void listCustomersCommand() {
        this.customerManager.listAllCustomers();
    }

    private void addNewCustomerCommand() {
        System.out.println("Enter Id for the new customer: ");
        String customerId = this.scanner.next();

        System.out.println("Enter the name of the customer: ");
        String name = this.scanner.next();

        System.out.println("Enter the address of the customer: ");
        String address = scanner.next();

        Customer newCustomer = new Customer(customerId, name, address);
        this.customerManager.addCustomer(newCustomer);
    }

    private void deleteCustomerCommand() {
        System.out.println("Enter the Id of the customer that you want to delete: ");
        String customerId = scanner.next();

        this.customerManager.deleteCustomer(customerId);
    }

    private void listAllAccountsByCustomerIdCommand() {
        System.out.println("Enter the Id of the customer: ");
        String customerId = scanner.next();

        this.customerManager.listAccountsByOwnerId(customerId);
    }

    private void addNewAccountCommand() {
        System.out.println("Enter account type: ");
        String accountType = scanner.next();

        System.out.println("Enter Iban: ");
        String iban = scanner.next();

        System.out.println("Enter owner Id: ");
        String ownerId = scanner.next();

        System.out.println("Enter the amount of money: ");
        double amount = scanner.nextDouble();

        AccountType type = AccountType.valueOf(accountType.toUpperCase());
        Account newAccount = new CurrentAccount(iban, ownerId, amount);
        this.customerManager.addAccount(newAccount, type);
    }

    private void deleteAccountCommand() {
        System.out.println("Enter the owner Id: ");
        String ownerId = scanner.next();

        System.out.println("Enter the Iban of the account that you want to delete: ");
        String iban = scanner.next();

        this.customerManager.deleteAccount(ownerId, iban);
    }

    private void withdrawFromAccountCommand() {
        System.out.println("Enter owner Id: ");
        String ownerId = this.scanner.next();

        System.out.println("Enter account Iban: ");
        String iban = scanner.next();

        System.out.println("Enter the amount: ");
        double amountToWithdraw = scanner.nextDouble();

        this.bank.withdrawFromAccount(ownerId, iban, amountToWithdraw);
    }

    private void depositToAccountCommand() {
        System.out.println("Enter owner Id: ");
        String ownerId = this.scanner.next();

        System.out.println("Enter Iban: ");
        String iban = scanner.next();

        System.out.println("Enter amount: ");
        double amountToDeposit = scanner.nextDouble();

        this.bank.depositToAccount(ownerId, iban, amountToDeposit);
    }

    private void transferCommand() {
        System.out.println("Enter sender Id: ");
        String customerSender = this.scanner.next();

        System.out.println("Enter sender account Iban");
        String senderIban = scanner.next();

        System.out.println("Enter receiver Id: ");
        String customerReceiver = this.scanner.next();

        System.out.println("Enter receiver account Iban");
        String receiverIban = scanner.next();

        System.out.println("Enter amount: ");
        double amountToTransfer = scanner.nextDouble();

        this.bank.transfer(customerSender, senderIban, customerReceiver, receiverIban, amountToTransfer);
    }

    private void displayInformationForTheBankCommand() {
        this.bank.displayBankInformation();
    }

    private void quitCommand() {
        shouldQuit = true;
        System.out.println("Quitting...");
    }
}