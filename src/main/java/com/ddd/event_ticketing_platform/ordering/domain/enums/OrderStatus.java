package com.ddd.event_ticketing_platform.ordering.domain.enums;

public enum OrderStatus {
    AWAITING_PAYMENT,
    PAID,
    PAYMENT_FAILED,
    EXPIRED,
    CANCELLED,
    REFUNDED
}
