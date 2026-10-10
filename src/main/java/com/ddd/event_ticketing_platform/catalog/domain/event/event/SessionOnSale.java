package com.ddd.event_ticketing_platform.catalog.domain.event.event;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.SessionId;

public class SessionOnSale {

    private SessionId sessionId;

    public SessionOnSale(SessionId sessionId) {
        this.sessionId = sessionId;
    }
}
