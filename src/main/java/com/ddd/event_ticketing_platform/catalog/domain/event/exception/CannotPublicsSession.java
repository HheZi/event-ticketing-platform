package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

public class CannotPublicsSession extends RuntimeException {
    public CannotPublicsSession(String message) {
        super(message);
    }
}
