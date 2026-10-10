package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

import com.ddd.event_ticketing_platform.sharedkernel.exception.InvariantException;

public class CannotPublishSession extends InvariantException {
    public CannotPublishSession(String message) {
        super(message);
    }
}
