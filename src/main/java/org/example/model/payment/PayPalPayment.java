package org.example.model.payment;

public class PayPalPayment implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        // Simulate payment processing
        return true;
    }
}