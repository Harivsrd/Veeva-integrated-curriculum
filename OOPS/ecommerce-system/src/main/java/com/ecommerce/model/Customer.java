package com.ecommerce.model;

public class Customer extends User {
    private String customerId;
    private String password; // Encapsulated credentials

    public Customer(String customerId, String name, String email, String phoneNo, Address address, String password) {
        super(name, email, phoneNo, address);
        this.customerId = customerId;
        this.password = password;
    }

    public String getCustomerId() { return customerId; }
    
    public boolean validatePassword(String pwd) {
        return this.password.equals(pwd);
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer [ID=" + customerId + ", Name=" + name + ", Email=" + email + ", Phone=" + phoneNo + "]";
    }
}
