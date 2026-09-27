package com.ddd.event_ticketing_platform.users.domain.model;

import jakarta.persistence.Embeddable;
import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
@Embeddable
public record BuyerId(Long id) {
}
