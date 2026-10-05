package com.ddd.event_ticketing_platform.catalog.domain.event.model;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record EventId(Long eventId) {
}
