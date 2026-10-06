package com.ddd.event_ticketing_platform.catalog.domain.venue.exception;

import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SectionId;

public class SectionNotFound extends RuntimeException {
    public SectionNotFound(SectionId id) {
        super("Section is not found by ID %s".formatted(id.sectionId()));
    }
}
