package com.ddd.event_ticketing_platform.catalog.application.query;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.TicketType;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.TicketTypeId;

import java.math.BigDecimal;
import java.util.Optional;

public record TicketTypeRule(
        TicketTypeId id, BigDecimal basePrice,
        Optional<Integer> maxPreOrder
) {

    public static TicketTypeRule from(TicketType type) {
        return new TicketTypeRule(type.id(), type.basePrice(), type.maxPreOrder());
    }

}
