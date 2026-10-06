package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

public class TicketsNotCoveringsAllSections extends RuntimeException {
    public TicketsNotCoveringsAllSections() {
        super("Tickets are covering all sections of the seating layout");
    }
}
