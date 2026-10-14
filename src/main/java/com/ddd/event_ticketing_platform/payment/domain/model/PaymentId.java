package com.ddd.event_ticketing_platform.payment.domain.model;

import java.util.UUID;

public record PaymentId(UUID paymentId) {
    public static PaymentId generate() {
        return new PaymentId(UUID.randomUUID());
    }
}
