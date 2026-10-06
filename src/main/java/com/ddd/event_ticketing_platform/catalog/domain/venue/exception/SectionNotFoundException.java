package com.ddd.event_ticketing_platform.catalog.domain.venue.exception;

import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SectionId;

public class SectionNotFoundException extends RuntimeException {
    public SectionNotFoundException(SectionId id) {
        super("Section is not found by ID %s".formatted(id.sectionId()));
    }
}
