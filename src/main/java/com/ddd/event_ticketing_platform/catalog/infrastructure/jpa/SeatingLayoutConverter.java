package com.ddd.event_ticketing_platform.catalog.infrastructure.jpa;

import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatingLayout;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.util.ArrayList;
import java.util.List;

@Converter
public class SeatingLayoutConverter
        implements AttributeConverter<List<SeatingLayout>, String> {

    private final static JsonMapper MAPPER = JsonMapper.builder().build();

    @Override
    public String convertToDatabaseColumn(List<SeatingLayout> seatingLayouts) {
        if (seatingLayouts == null || seatingLayouts.isEmpty()) return null;

        return MAPPER.writeValueAsString(seatingLayouts);
    }

    @Override
    public List<SeatingLayout> convertToEntityAttribute(String json) {
        if (json == null) return new ArrayList<>();

        return MAPPER.convertValue(json, new TypeReference<List<SeatingLayout>>() {
        });
    }
}
