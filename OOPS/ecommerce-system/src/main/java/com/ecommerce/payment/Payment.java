package com.ecommerce.payment;

public interface Payment {
    boolean processPayment(double amount);
    String getPaymentModeName();
}
