package com.ecommerce.payment;

public class CodPayment implements Payment {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Cash on Delivery (COD) selected for amount: $" + String.format("%.2f", amount) + ". Payable upon delivery.");
        return true;
    }

    @Override
    public String getPaymentModeName() {
        return "Cash on Delivery (COD)";
    }
}
