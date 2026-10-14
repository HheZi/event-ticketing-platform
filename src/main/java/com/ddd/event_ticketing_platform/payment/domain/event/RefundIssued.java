package com.ddd.event_ticketing_platform.payment.domain.event;

import com.ddd.event_ticketing_platform.payment.domain.model.PaymentId;

public record RefundIssued(PaymentId paymentId) {
}
