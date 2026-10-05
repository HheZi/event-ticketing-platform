package com.ddd.event_ticketing_platform.catalog.domain.venue.model;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.UUID;

@ValueObject
public record SectionId(UUID sectionId) {
    public static SectionId generate() {
        return new SectionId(UUID.randomUUID());
    }
}
