package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

import com.ddd.event_ticketing_platform.sharedkernel.exception.InvariantException;

public class TicketsNotCoveringsAllSections extends InvariantException {
    public TicketsNotCoveringsAllSections() {
        super("Tickets are covering all sections of the seating layout");
    }
}
