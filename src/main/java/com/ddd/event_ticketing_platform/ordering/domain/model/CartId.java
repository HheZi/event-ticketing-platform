package com.ddd.event_ticketing_platform.ordering.domain.model;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record CartId(Long cartId) {
}
