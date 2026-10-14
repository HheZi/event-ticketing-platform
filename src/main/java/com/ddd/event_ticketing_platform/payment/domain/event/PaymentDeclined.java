package com.ddd.event_ticketing_platform.payment.domain.event;

import com.ddd.event_ticketing_platform.payment.domain.model.PaymentId;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public record PaymentDeclined(PaymentId paymentId) {
}
