package com.ddd.event_ticketing_platform.catalog.application.exception;

import com.ddd.event_ticketing_platform.catalog.domain.model.VenueId;

public class VenueIsNotFound extends RuntimeException {
    public VenueIsNotFound(VenueId venueId) {
        super("Venue is not found by ID " + venueId.id());
    }
}
