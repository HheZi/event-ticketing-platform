package com.ddd.event_ticketing_platform.catalog.domain.venue.exception;

import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatingLayoutId;

public class SeatingLayoutNotFoundException extends RuntimeException {
    public SeatingLayoutNotFoundException(SeatingLayoutId id) {
        super("Seating layout is not found by ID %s".formatted(id.seatingLayoutId()));
    }
}
