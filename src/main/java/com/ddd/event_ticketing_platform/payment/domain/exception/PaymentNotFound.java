package com.ddd.event_ticketing_platform.payment.domain.exception;

import com.ddd.event_ticketing_platform.payment.domain.model.PaymentId;
import com.ddd.event_ticketing_platform.sharedkernel.exception.NotFoundException;

public class PaymentNotFound extends NotFoundException {
    public PaymentNotFound(PaymentId paymentId) {
        super("Payment by ID %s is not found".formatted(paymentId.paymentId()));
    }
}
