package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

public class TicketsNotCoveringsAllSectionsException extends RuntimeException {
    public TicketsNotCoveringsAllSectionsException() {
        super("Tickets are covering all sections of the seating layout");
    }
}
