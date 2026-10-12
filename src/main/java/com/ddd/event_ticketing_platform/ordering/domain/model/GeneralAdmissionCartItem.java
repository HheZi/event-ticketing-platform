package com.ddd.event_ticketing_platform.ordering.domain.model;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.TicketTypeId;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GENERAL_ADMISSION")
public class GeneralAdmissionCartItem extends CartItem {

    private Integer quantity;

    public GeneralAdmissionCartItem(Cart cart, TicketTypeId ticketTypeId, Integer quantity) {
        super(cart, ticketTypeId);
        this.quantity = quantity;
    }

    private GeneralAdmissionCartItem() {
        super();
    }

    public Integer quantity() {
        return quantity;
    }

}
