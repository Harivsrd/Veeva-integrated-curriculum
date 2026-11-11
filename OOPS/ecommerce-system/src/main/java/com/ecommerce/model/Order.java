package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private Customer customer;
    private List<Product> items;
    private double orderAmount;
    private double shippingCharge;
    private OrderStatus status;
    private String trackingDetails;

    public Order(String orderId, Customer customer, List<Product> items, double shippingCharge) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>(items);
        this.orderAmount = items.stream().mapToDouble(Product::getPrice).sum();
        this.shippingCharge = shippingCharge;
        this.status = OrderStatus.CREATED_AND_RECEIVED;
        this.trackingDetails = "Order created and received. Awaiting dispatch.";
    }

    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public List<Product> getItems() { return items; }
    
    // Requirement 9: Order Total = order amount + shipping charge
    public double getOrderAmount() { return orderAmount; }
    public double getShippingCharge() { return shippingCharge; }
    public double getTotalAmount() { return orderAmount + shippingCharge; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public String getTrackingDetails() { return trackingDetails; }
    public void setTrackingDetails(String trackingDetails) { this.trackingDetails = trackingDetails; }

    public void updateStatus(OrderStatus status, String trackingDetails) {
        this.status = status;
        if (trackingDetails != null && !trackingDetails.isEmpty()) {
            this.trackingDetails = trackingDetails;
        }
    }

    public void printSummary() {
        System.out.println("========================================");
        System.out.println("          ORDER SUMMARY [" + orderId + "]");
        System.out.println("========================================");
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Shipping Address: " + customer.getAddress());
        System.out.println("Items Purchased:");
        for (Product p : items) {
            System.out.println("  - " + p);
        }
        System.out.printf("Order Amount   : $%.2f%n", orderAmount);
        System.out.printf("Shipping Charge: $%.2f%n", shippingCharge);
        System.out.printf("Total Amount   : $%.2f%n", getTotalAmount());
        System.out.println("Current Status : " + status);
        System.out.println("Tracking Info  : " + trackingDetails);
        System.out.println("========================================");
    }
}
