package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

public class CannotPublicsSessionException extends RuntimeException {
    public CannotPublicsSessionException(String message) {
        super(message);
    }
}
