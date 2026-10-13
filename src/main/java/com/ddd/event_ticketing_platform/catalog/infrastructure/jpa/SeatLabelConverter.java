package com.ddd.event_ticketing_platform.catalog.infrastructure.jpa;

import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatLabel;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SeatLabelConverter implements AttributeConverter<SeatLabel, String> {
    @Override
    public String convertToDatabaseColumn(SeatLabel seatLabel) {
        if (seatLabel == null) return null;

        return seatLabel.displayName();
    }

    @Override
    public SeatLabel convertToEntityAttribute(String dbData) {
        return SeatLabel.from(dbData);
    }
}
