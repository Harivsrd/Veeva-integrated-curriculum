package com.ecommerce.service;

import com.ecommerce.model.*;
import com.ecommerce.payment.Payment;
import com.ecommerce.repository.CustomerDatabase;
import com.ecommerce.repository.ProductCatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcommercePlatform {
    private CustomerDatabase customerDb = new CustomerDatabase();
    private ProductCatalog catalog = new ProductCatalog();
    private Map<String, Order> orders = new HashMap<>();
    private Map<String, ShoppingCart> customerCarts = new HashMap<>();

    public CustomerDatabase getCustomerDb() { return customerDb; }
    public ProductCatalog getCatalog() { return catalog; }

    public boolean registerCustomer(String id, String name, String email, String phone, Address address, String password) {
        if (customerDb.findByEmail(email) != null) {
            System.out.println("Registration failed: Email already exists.");
            return false;
        }
        Customer customer = new Customer(id, name, email, phone, address, password);
        customerDb.addCustomer(customer);
        customerCarts.put(id, new ShoppingCart());
        System.out.println("Customer registered successfully: " + name);
        return true;
    }

    public Customer login(String email, String password) {
        Customer customer = customerDb.findByEmail(email);
        if (customer != null && customer.validatePassword(password)) {
            System.out.println("Login successful for: " + customer.getName());
            return customer;
        }
        System.out.println("Login failed: Invalid email or password.");
        return null;
    }

    public ShoppingCart getCartForCustomer(String customerId) {
        return customerCarts.computeIfAbsent(customerId, k -> new ShoppingCart());
    }

    public Order checkoutOrder(String orderId, Customer customer, double shippingCharge, Payment paymentMethod) {
        ShoppingCart cart = getCartForCustomer(customer.getCustomerId());
        if (cart.isEmpty()) {
            System.out.println("Checkout failed: Cart is empty.");
            return null;
        }

        List<Product> purchasedItems = cart.getItems();
        double totalAmount = cart.calculateSubtotal() + shippingCharge;

        // Process payment polymorphically
        if (paymentMethod.processPayment(totalAmount)) {
            // Deduct stock
            for (Product p : purchasedItems) {
                p.reduceStock(1);
            }

            Order order = new Order(orderId, customer, purchasedItems, shippingCharge);
            orders.put(orderId, order);
            cart.clear(); // Empty cart after successful order
            System.out.println("Order successfully placed! Order ID: " + orderId);
            return order;
        } else {
            System.out.println("Payment failed. Order could not be placed.");
            return null;
        }
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }
}
