package com.ddd.event_ticketing_platform.catalog.domain.model;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record SeatLabel(String sectionCode, String rowLabel, Integer seatNumber) {

    public String displayName() {
        return sectionCode + "-" + rowLabel + "-" + seatNumber;
    }

}
