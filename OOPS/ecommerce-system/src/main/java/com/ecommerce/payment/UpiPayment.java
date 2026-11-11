package com.ecommerce.payment;

public class UpiPayment implements Payment {
    private String upiId;

    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing UPI payment of $" + String.format("%.2f", amount) + " using UPI ID: " + upiId);
        return true;
    }

    @Override
    public String getPaymentModeName() {
        return "UPI (" + upiId + ")";
    }
}
