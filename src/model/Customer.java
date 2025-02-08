package model;
public class Customer {
    private String code;
    private String name;
    private String phoneNumber;
    private String email;

    public Customer() {}

    public Customer(String code, String name, String phoneNumber, String email) {
        this.code = code;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        return String.format("| %-7s| %-22s| %-13s| %-20s|",code,name,phoneNumber,email);
    }
    
    
    
}
