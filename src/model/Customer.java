package model;

import java.io.Serializable;

public class Customer implements Serializable, Comparable<Customer> {

    private String customerCode;
    private String name;
    private String phoneNumber;
    private String email;

    public Customer() {
    }

    public Customer(String customerCode) {
        this.customerCode = customerCode;
    }

    public Customer(String customerCode, String name, String phoneNumber, String email) {
        this.customerCode = customerCode;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("| %-7s| %-22s| %-13s| %-20s|", customerCode, name, phoneNumber, email);
    }

    @Override
    public int compareTo(Customer o) {
        int resultEachSort = this.name.compareTo(o.name);
        //if - else
        return resultEachSort;
    }
}
