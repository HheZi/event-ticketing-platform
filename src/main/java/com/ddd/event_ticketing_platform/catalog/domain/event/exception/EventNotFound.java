package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.EventId;
import com.ddd.event_ticketing_platform.sharedkernel.exception.NotFoundException;

public class EventNotFound extends NotFoundException {
    public EventNotFound(EventId id) {
        super("Event is not found by ID %d".formatted(id.eventId()));
    }
}
