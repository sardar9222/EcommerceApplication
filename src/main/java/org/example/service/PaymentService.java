package org.example.service;

import org.example.model.payment.CreditCardPayment;
import org.example.model.payment.PayPalPayment;
import org.example.model.payment.PaymentProcessor;


public class PaymentService {
    public boolean processPayment(PaymentProcessor paymentProcessor, double amount) {
        return paymentProcessor.processPayment(amount);
    }
}