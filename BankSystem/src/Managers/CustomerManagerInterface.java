package Managers;

import Accounts.Account;
import Accounts.AccountType;
import Customers.Customer;

public interface CustomerManagerInterface {

    void addCustomer(Customer newCustomer);

    void deleteCustomer(String ownerId);

    void listAllCustomers();

    void listAccountsByOwnerId(String ownerId);

    void addAccount(Account account, AccountType type);

    void deleteAccount(String ownerId, String iban);

    Account getAccount(String ownerId, String iban);

}