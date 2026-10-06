package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

public class UnknowSectionType extends RuntimeException {
    public UnknowSectionType() {
        super("Unknow section type");
    }
}
