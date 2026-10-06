package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.SessionId;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(SessionId sessionId) {
        super("Session with ID %d is not found".formatted(sessionId.sessionId()));
    }
}
