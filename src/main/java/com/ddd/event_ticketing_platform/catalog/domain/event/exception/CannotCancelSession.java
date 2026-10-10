package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

import com.ddd.event_ticketing_platform.sharedkernel.exception.InvariantException;

public class CannotCancelSession extends InvariantException {
    public CannotCancelSession(String message) {
        super(message);
    }
}
