package com.ddd.event_ticketing_platform.catalog.domain.event.event;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.SessionId;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public record SessionPublishedEvent(SessionId sessionId) {

}
