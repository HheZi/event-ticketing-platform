package com.ddd.event_ticketing_platform.sharedkernel.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
