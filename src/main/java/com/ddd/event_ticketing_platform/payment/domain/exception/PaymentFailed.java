package com.ddd.event_ticketing_platform.payment.domain.exception;

public class PaymentFailed extends RuntimeException {
    public PaymentFailed() {
        super("Payment failed");
    }
}
