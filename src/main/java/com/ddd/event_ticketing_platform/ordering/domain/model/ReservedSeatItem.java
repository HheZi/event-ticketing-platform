package com.ddd.event_ticketing_platform.ordering.domain.model;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.TicketTypeId;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatLabel;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import org.jmolecules.ddd.annotation.ValueObject;

@Entity
@ValueObject
@DiscriminatorValue("RESERVED")
public class ReservedSeatItem extends CartItem {

    @Embedded
    private SeatLabel seatLabel;

    public ReservedSeatItem(Cart cart, TicketTypeId ticketTypeId, SeatLabel seatLabel) {
        super(cart, ticketTypeId);
        this.seatLabel = seatLabel;
    }

    private ReservedSeatItem() {
        super();
    }

    public SeatLabel seatLabel() {
        return seatLabel;
    }
}
