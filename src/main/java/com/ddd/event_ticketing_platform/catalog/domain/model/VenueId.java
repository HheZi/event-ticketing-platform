package com.ddd.event_ticketing_platform.catalog.domain.model;

import jakarta.persistence.Embeddable;
import org.jmolecules.ddd.annotation.ValueObject;

@Embeddable
@ValueObject
public record VenueId(Long id) {
}
