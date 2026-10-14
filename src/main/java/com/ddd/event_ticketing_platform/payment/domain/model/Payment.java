package com.ddd.event_ticketing_platform.payment.domain.model;

import com.ddd.event_ticketing_platform.ordering.domain.model.OrderId;
import com.ddd.event_ticketing_platform.payment.domain.enums.PaymentStatus;
import com.ddd.event_ticketing_platform.payment.domain.event.*;
import com.ddd.event_ticketing_platform.payment.domain.exception.PaymentNotCaptured;
import jakarta.persistence.*;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.math.BigDecimal;

@Entity
@AggregateRoot
public class Payment extends AbstractAggregateRoot<Payment> {

    @EmbeddedId
    private PaymentId id;

    private BigDecimal amount;

    @Embedded
    private OrderId orderId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Embedded
    private PaymentDetailsId detailsId;

    public Payment(OrderId orderId, BigDecimal amount) {
        this.id = PaymentId.generate();
        this.amount = amount;
        this.orderId = orderId;
        this.status = PaymentStatus.INITIATED;

        registerEvent(new PaymentInitiated(id));
    }

    private Payment() {

    }

    public void capture(PaymentDetailsId detailsId) {
        this.status = PaymentStatus.CAPTURED;

        this.detailsId = detailsId;
        registerEvent(new PaymentCaptured(id));
    }

    public void decline() {
        this.status = PaymentStatus.DECLINED;

        registerEvent(new PaymentDeclined(id));
    }

    public void fail() {
        this.status = PaymentStatus.FAILED;

        registerEvent(new PaymentFailed(id));
    }

    public void refund() {
        this.status = PaymentStatus.REFUNDED;

        registerEvent(new PaymentRefunded(id));
    }

    public void issueRefund() {
        if (status != PaymentStatus.CAPTURED) {
            throw new PaymentNotCaptured();
        }

        registerEvent(new RefundIssued(id));
    }

    public PaymentId id() {
        return id;
    }

    public BigDecimal amount() {
        return amount;
    }

    public OrderId orderId() {
        return orderId;
    }

    public PaymentStatus status() {
        return status;
    }

    public PaymentDetailsId detailsId() {
        return detailsId;
    }
}
