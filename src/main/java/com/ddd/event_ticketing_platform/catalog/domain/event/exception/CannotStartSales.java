package com.ddd.event_ticketing_platform.catalog.domain.event.exception;

import com.ddd.event_ticketing_platform.sharedkernel.exception.InvariantException;

public class CannotStartSales extends InvariantException {
    public CannotStartSales(String message) {
        super(message);
    }
}
