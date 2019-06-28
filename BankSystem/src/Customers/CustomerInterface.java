package Customers;

public interface CustomerInterface {

    String getId();

    String getName();

    String getAddress();

    void setId(String id);

    void setName(String name);

    void setAddress(String address);

    void displayCustomerInformation();

}