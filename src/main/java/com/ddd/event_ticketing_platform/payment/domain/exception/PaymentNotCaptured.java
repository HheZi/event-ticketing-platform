package com.ddd.event_ticketing_platform.payment.domain.exception;

import com.ddd.event_ticketing_platform.sharedkernel.exception.InvariantException;

public class PaymentNotCaptured extends InvariantException {
    public PaymentNotCaptured() {
        super("Payment is not captured");
    }
}
