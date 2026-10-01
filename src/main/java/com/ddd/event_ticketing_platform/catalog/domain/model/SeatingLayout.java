package com.ddd.event_ticketing_platform.catalog.domain.model;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public class SeatingLayout {

    private final SeatingLayoutId id;

    private final String name;

    private final Section section;

    public SeatingLayout(String name, Section section) {
        this.id = SeatingLayoutId.generate();
        this.name = name;
        this.section = section;
    }

    public SeatingLayoutId id() {
        return id;
    }

    public Section section() {
        return section;
    }

    public String name() {
        return name;
    }
}
