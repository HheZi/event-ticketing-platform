package com.ddd.event_ticketing_platform.payment.domain.exception;

public class PaymentDeclined extends RuntimeException {
    public PaymentDeclined() {
        super("Payment declined");
    }
}
