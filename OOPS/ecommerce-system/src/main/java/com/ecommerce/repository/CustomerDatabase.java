package com.ecommerce.repository;

import com.ecommerce.model.Customer;
import java.util.*;
import java.util.stream.Collectors;

public class CustomerDatabase {
    private Map<String, Customer> customers = new HashMap<>();

    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public void removeCustomer(String customerId) {
        customers.remove(customerId);
    }

    public void updateCustomer(Customer customer) {
        if (customers.containsKey(customer.getCustomerId())) {
            customers.put(customer.getCustomerId(), customer);
        }
    }

    public Customer getCustomerById(String customerId) {
        return customers.get(customerId);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    // Queries requested in Requirement 2
    public List<Customer> findByName(String name) {
        return customers.values().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Customer> findByCity(String city) {
        return customers.values().stream()
                .filter(c -> c.getAddress() != null && c.getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public Customer findByPhone(String phoneNo) {
        return customers.values().stream()
                .filter(c -> c.getPhoneNo().equals(phoneNo))
                .findFirst().orElse(null);
    }

    public Customer findByEmail(String email) {
        return customers.values().stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst().orElse(null);
    }
}
