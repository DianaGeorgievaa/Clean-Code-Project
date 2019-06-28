package Managers;

import Accounts.*;
import Customers.Customer;
import Customers.CustomerInterface;
import Exceptions.*;

import java.util.*;

public class CustomerManager implements CustomerManagerInterface {

    private Map<Customer, List<Account>> customerManager;

    public CustomerManager() {
        this.customerManager = new HashMap<>();
    }

    /**
     * If the customer does not exist, add him to the Bank system
     *
     * @param newCustomer
     * @throws CustomerAlreadyExistsException
     */
    @Override
    public void addCustomer(Customer newCustomer) {
        Customer customer = this.checkCustomerExistence(newCustomer.getId());
        if (customer != null) {
            throw new CustomerAlreadyExistsException("The customer already exists!");
        }
        this.customerManager.put(newCustomer, new ArrayList<>());
        System.out.println("The customer was successfully added!");
    }

    /**
     * If the customer exists, remove him from the Bank system
     *
     * @param ownerId
     */
    @Override
    public void deleteCustomer(String ownerId) {
        Customer customer = this.getCustomer(ownerId);
        this.customerManager.remove(customer);
        System.out.println("The customer was successfully deleted!");
    }

    /**
     * If the Bank system is not empty, print all customers
     */
    @Override
    public void listAllCustomers() {
        if (this.customerManager.size() == 0) {
            System.out.println("No customers in the bank!");
            return;
        }
        System.out.println("All customers: ");
        for (Customer customer : this.customerManager.keySet()) {
            customer.displayCustomerInformation();
        }
    }

    /**
     * If the customer exists, list his accounts
     *
     * @param ownerId
     */
    @Override
    public void listAccountsByOwnerId(String ownerId) {
        Customer currentCustomer = this.getCustomer(ownerId);

        List<Account> currentCustomerAccounts = this.customerManager.get(currentCustomer);
        if (currentCustomerAccounts.size() == 0) {
            System.out.println("The current customer has no accounts!");
            return;
        }
        for (Account account : currentCustomerAccounts) {
            account.displayAccountInformation();
        }
    }

    /**
     * If the customer exists, add new account to his list with accounts
     *
     * @param account
     * @param type
     * @throws AccountAlreadyExistsException
     */
    @Override
    public void addAccount(Account account, AccountType type) {
        Customer currentCustomer = this.getCustomer(account.getOwnerId());

        Account currentAccount = this.getAccountByCustomer(currentCustomer, account.getIban());
        if (currentAccount != null) {
            throw new AccountAlreadyExistsException("The account already exists!");
        }

        Account newAccount = this.createAccount(account, type);
        List<Account> currentCustomerAccounts = this.customerManager.get(currentCustomer);
        currentCustomerAccounts.add(newAccount);
        this.customerManager.put(currentCustomer, currentCustomerAccounts);
    }

    /**
     * If the customer and the account are existing, delete the account
     *
     * @param ownerId
     * @param iban
     */
    @Override
    public void deleteAccount(String ownerId, String iban) {
        Customer currentCustomer = this.getCustomer(ownerId);

        Account currentAccount = this.getAccountByCustomer(currentCustomer, iban);
        if (currentAccount == null) {
            throw new AccountNotFoundException("No such account in the bank!");
        }

        List<Account> currentCustomerAccounts = this.customerManager.get(currentCustomer);
        currentCustomerAccounts.remove(currentAccount);
        this.customerManager.put(currentCustomer, currentCustomerAccounts);
    }

    /**
     * If the customer exists, check if he has account with the same Iban and return it
     *
     * @param ownerId
     * @param iban
     * @return account
     * @throws AccountNotFoundException
     */
    @Override
    public Account getAccount(String ownerId, String iban) {
        Customer currentCustomer = this.getCustomer(ownerId);

        Account account = this.getAccountByCustomer(currentCustomer, iban);
        if (account == null) {
            throw new AccountNotFoundException("No such account in the bank!");
        }
        return account;
    }

    private Account getAccountByCustomer(Customer customer, String iban) {
        List<Account> currentCustomerAccounts = this.customerManager.get(customer);
        for (Account account : currentCustomerAccounts) {
            if (account.getIban().equals(iban)) {
                return account;
            }
        }
        return null;
    }

    private Customer checkCustomerExistence(String customerId) {
        for (Customer customer : this.customerManager.keySet()) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private Customer getCustomer(String ownerId) {
        Customer customer = this.checkCustomerExistence(ownerId);
        if (customer == null) {
            throw new CustomerNotFoundException("No such customer in the bank!");
        }
        return customer;
    }

    private Account createAccount(Account account, AccountType type) {
        Scanner scanner = new Scanner(System.in);

        switch (type) {
            case PRIVILEGE:
                System.out.println("Enter overdraft: ");
                double overdraft = scanner.nextDouble();
                return new PrivilegeAccount(account.getIban(), account.getOwnerId(), account.getBalance(), overdraft);
            case SAVINGS:
                System.out.println("Enter interest rate: ");
                double interestRate = scanner.nextDouble();
                return new SavingsAccount(account.getIban(), account.getOwnerId(), account.getBalance(), interestRate);
            case CURRENT:
                return new CurrentAccount(account.getIban(), account.getOwnerId(), account.getBalance());
            default:
                throw new InvalidTypeException("No such account type!");
        }
    }
}