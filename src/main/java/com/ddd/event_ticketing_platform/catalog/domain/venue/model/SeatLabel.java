package com.ddd.event_ticketing_platform.catalog.domain.venue.model;

import jakarta.annotation.Nullable;
import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record SeatLabel(String sectionCode, String rowLabel, Integer seatNumber) {

    public String displayName() {
        return sectionCode + "-" + rowLabel + "-" + seatNumber;
    }

    public static @Nullable SeatLabel from(@Nullable String seatLabel) {
        if (seatLabel == null) return null;

        String[] split = seatLabel.split("-");

        if (split.length != 3) throw new IllegalArgumentException("String argument is not of format of seat label");

        return new SeatLabel(split[0], split[1], Integer.parseInt(split[2]));
    }
}
