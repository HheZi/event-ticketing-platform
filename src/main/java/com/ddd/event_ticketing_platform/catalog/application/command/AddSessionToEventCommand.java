package com.ddd.event_ticketing_platform.catalog.application.command;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.TicketType;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatingLayoutId;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SectionId;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.VenueId;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public record AddSessionToEventCommand(
        VenueId venueId, SeatingLayoutId seatingLayoutId,
        Instant startTime, Instant salesStart, Instant salesEnd,
        List<TicketTypeInfo> tickets
) {

    public List<TicketType> toTicketTypes() {
        return tickets.stream()
                .map(TicketTypeInfo::toTicketType)
                .collect(Collectors.toList());
    }

    public record TicketTypeInfo(
            String name, SectionId sectionId, BigDecimal basePrice,
            @Nullable Integer maxPreOrder
    ) {

        private TicketType toTicketType() {
            return new TicketType(name, sectionId, basePrice, maxPreOrder);
        }

    }
}
