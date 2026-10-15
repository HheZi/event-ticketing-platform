package com.ddd.event_ticketing_platform.ordering.domain.model;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.TicketTypeId;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatLabel;
import com.ddd.event_ticketing_platform.catalog.infrastructure.jpa.SeatingLayoutConverter;
import com.ddd.event_ticketing_platform.ordering.domain.enums.OrderLineStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderLine {

    @EmbeddedId
    private OrderLineId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Order order;

    @Embedded
    private TicketTypeId ticketTypeId;

    @Convert(converter = SeatingLayoutConverter.class)
    private SeatLabel seatLabel;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private OrderLineStatus status;

    public OrderLine(TicketTypeId ticketTypeId, SeatLabel seatLabel, BigDecimal price) {
        this.ticketTypeId = ticketTypeId;
        this.seatLabel = seatLabel;
        this.price = price;
        this.status = OrderLineStatus.ACTIVE;
    }

    private OrderLine() {

    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderLineId id() {
        return id;
    }

    public TicketTypeId ticketTypeId() {
        return ticketTypeId;
    }

    public SeatLabel seatLabel() {
        return seatLabel;
    }

    public BigDecimal price() {
        return price;
    }

    public OrderLineStatus status() {
        return status;
    }
}
