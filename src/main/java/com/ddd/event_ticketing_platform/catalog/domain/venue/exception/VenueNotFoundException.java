package com.ddd.event_ticketing_platform.catalog.domain.venue.exception;

import com.ddd.event_ticketing_platform.catalog.domain.venue.model.VenueId;

public class VenueNotFoundException extends RuntimeException {
    public VenueNotFoundException(VenueId venueId) {
        super("Venue is not found by ID " + venueId.venueId());
    }
}
