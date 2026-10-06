package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.EventId;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(EventId id) {
        super("Event is not found by ID %d".formatted(id.eventId()));
    }
}
