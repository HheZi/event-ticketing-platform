package com.ddd.event_ticketing_platform.catalog.domain.event.event;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.SessionId;

public class SessionCancelled {

    private SessionId sessionId;

    public SessionCancelled(SessionId sessionId) {
        this.sessionId = sessionId;
    }

    public SessionId getSessionId() {
        return sessionId;
    }

}
