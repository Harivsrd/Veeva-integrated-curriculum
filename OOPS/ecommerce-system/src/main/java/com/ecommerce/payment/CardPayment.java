package com.ecommerce.payment;

public class CardPayment implements Payment {
    private String cardNumber;
    private String cardHolderName;

    public CardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public boolean processPayment(double amount) {
        String maskedCard = "****-****-****-" + cardNumber.substring(Math.max(0, cardNumber.length() - 4));
        System.out.println("Processing Credit/Debit Card payment of $" + String.format("%.2f", amount) + " for card " + maskedCard);
        return true;
    }

    @Override
    public String getPaymentModeName() {
        return "Credit/Debit Card";
    }
}
