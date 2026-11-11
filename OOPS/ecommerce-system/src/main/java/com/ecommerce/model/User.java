package com.ecommerce.model;

public abstract class User {
    protected String name;
    protected String email;
    protected String phoneNo;
    protected Address address;

    public User(String name, String email, String phoneNo, Address address) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
}
