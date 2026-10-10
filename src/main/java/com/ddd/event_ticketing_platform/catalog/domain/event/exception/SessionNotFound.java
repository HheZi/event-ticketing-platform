package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.SessionId;
import com.ddd.event_ticketing_platform.sharedkernel.exception.NotFoundException;

public class SessionNotFound extends NotFoundException {
    public SessionNotFound(SessionId sessionId) {
        super("Session with ID %d is not found".formatted(sessionId.sessionId()));
    }
}
