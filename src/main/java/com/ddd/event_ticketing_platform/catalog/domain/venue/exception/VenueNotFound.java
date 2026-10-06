package com.ddd.event_ticketing_platform.catalog.domain.venue.exception;

import com.ddd.event_ticketing_platform.catalog.domain.venue.model.VenueId;

public class VenueNotFound extends RuntimeException {
    public VenueNotFound(VenueId venueId) {
        super("Venue is not found by ID " + venueId.venueId());
    }
}
