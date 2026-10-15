package com.ddd.event_ticketing_platform.ordering.domain.model;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.SessionId;
import com.ddd.event_ticketing_platform.ordering.domain.enums.OrderStatus;
import com.ddd.event_ticketing_platform.payment.domain.model.PaymentId;
import com.ddd.event_ticketing_platform.users.domain.model.BuyerId;
import jakarta.persistence.*;
import org.jmolecules.ddd.annotation.AggregateRoot;

import java.util.List;

@Entity
@AggregateRoot
@Table(name = "_order")
public class Order {

    @EmbeddedId
    private OrderId id;

    @Embedded
    private BuyerId buyerId;

    @Embedded
    private SessionId sessionId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderLine> lines;

    @Embedded
    private HoldId holdId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Embedded
    private PaymentId paymentId;

    public Order(BuyerId buyerId, SessionId sessionId, HoldId holdId) {
        this.buyerId = buyerId;
        this.sessionId = sessionId;
        this.holdId = holdId;
        this.status = OrderStatus.AWAITING_PAYMENT;
    }

    private Order() {

    }

    public OrderId id() {
        return id;
    }

    public BuyerId buyerId() {
        return buyerId;
    }

    public SessionId sessionId() {
        return sessionId;
    }

    public List<OrderLine> lines() {
        return lines;
    }

    public HoldId holdId() {
        return holdId;
    }

    public OrderStatus status() {
        return status;
    }

    public PaymentId paymentId() {
        return paymentId;
    }
}
