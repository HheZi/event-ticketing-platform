package com.ddd.event_ticketing_platform.catalog.domain.venue.exception;

import com.ddd.event_ticketing_platform.catalog.domain.venue.model.VenueId;
import com.ddd.event_ticketing_platform.sharedkernel.exception.NotFoundException;

public class VenueNotFound extends NotFoundException {
    public VenueNotFound(VenueId venueId) {
        super("Venue is not found by ID " + venueId.venueId());
    }
}
