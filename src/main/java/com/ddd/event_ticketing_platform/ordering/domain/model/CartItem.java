package com.ddd.event_ticketing_platform.ordering.domain.model;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.TicketTypeId;
import jakarta.persistence.*;
import org.jmolecules.ddd.annotation.ValueObject;

@Entity
@ValueObject
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class CartItem {

    @EmbeddedId
    private CartItemId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Cart cart;

    @Embedded
    private TicketTypeId ticketTypeId;

    public CartItem(Cart cart, TicketTypeId ticketTypeId) {
        this.cart = cart;
        this.ticketTypeId = ticketTypeId;
    }

    protected CartItem() {
    }

    public CartItemId getId() {
        return id;
    }

    public TicketTypeId ticketTypeId() {
        return ticketTypeId;
    }

}
