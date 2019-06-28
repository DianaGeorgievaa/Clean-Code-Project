package Bank;

public interface BankInterface {

    void setName(String name);

    void setAddress(String address);

    String getName();

    String getAddress();

    void displayBankInformation();

    void withdrawFromAccount(String ownerId, String iban, double amount);

    void depositToAccount(String ownerId, String iban, double amount);

    void transfer(String ownerId, String fromIban, String customerIdToTransfer, String toIban, double amount);
}