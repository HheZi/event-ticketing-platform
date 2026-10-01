package com.ddd.event_ticketing_platform.catalog.domain.model;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.UUID;

@ValueObject
public record SeatingLayoutId(UUID id) {
    public static SeatingLayoutId generate() {
        return new SeatingLayoutId(UUID.randomUUID());
    }
}
