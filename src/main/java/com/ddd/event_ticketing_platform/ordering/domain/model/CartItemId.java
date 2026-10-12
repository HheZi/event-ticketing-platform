package com.ddd.event_ticketing_platform.ordering.domain.model;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.UUID;

@ValueObject
public record CartItemId(UUID cartItemId) {
}
