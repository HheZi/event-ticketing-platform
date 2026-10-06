package com.ddd.event_ticketing_platform.catalog.domain.venue.model;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.List;

@ValueObject
public class SeatingLayout {

    private SeatingLayoutId id;

    private String name;

    private List<Section> sections;

    public SeatingLayout(String name, List<Section> sections) {
        this.id = SeatingLayoutId.generate();
        this.name = name;
        this.sections = sections;
    }

    private SeatingLayout() {

    }

    public SeatingLayoutId id() {
        return id;
    }

    public List<Section> sections() {
        return sections;
    }

    public String name() {
        return name;
    }

}
