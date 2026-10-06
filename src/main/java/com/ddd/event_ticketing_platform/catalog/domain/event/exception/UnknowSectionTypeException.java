package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

public class UnknowSectionTypeException extends RuntimeException {
    public UnknowSectionTypeException() {
        super("Unknow section type");
    }
}
