package com.ddd.event_ticketing_platform.catalog.domain.venue.exception;

import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatingLayoutId;
import com.ddd.event_ticketing_platform.sharedkernel.exception.NotFoundException;

public class SeatingLayoutNotFound extends NotFoundException {
    public SeatingLayoutNotFound(SeatingLayoutId id) {
        super("Seating layout is not found by ID %s".formatted(id.seatingLayoutId()));
    }
}
