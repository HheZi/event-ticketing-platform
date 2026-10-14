package com.ddd.event_ticketing_platform.payment.application;

import com.ddd.event_ticketing_platform.ordering.domain.model.OrderId;
import com.ddd.event_ticketing_platform.payment.domain.exception.PaymentNotFound;
import com.ddd.event_ticketing_platform.payment.domain.model.Payment;
import com.ddd.event_ticketing_platform.payment.domain.model.PaymentId;
import com.ddd.event_ticketing_platform.payment.domain.repository.PaymentRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class PaymentGateway {

    private final PaymentRepository payments;

    public PaymentGateway(PaymentRepository payments) {
        this.payments = payments;
    }

    @Transactional
    public PaymentId charge(OrderId orderId, BigDecimal amount) {
        Payment payment = new Payment(orderId, amount);

        return payments.save(payment).id();
    }

    @Transactional
    public PaymentId refund(PaymentId paymentId) {
        Payment payment = payments.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFound(paymentId));

        payment.issueRefund();

        return payments.save(payment).id();
    }

}
