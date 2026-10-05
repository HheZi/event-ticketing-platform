package com.ddd.event_ticketing_platform.catalog.domain.venue.model;

import org.jmolecules.ddd.annotation.ValueObject;

import java.util.List;

@ValueObject
public non-sealed class ReservedSection implements Section {

    private SectionId id;

    private String name;

    private String code;

    private List<Row> rows;

    public ReservedSection(String name, String code, List<Row> rows) {
        this.id = SectionId.generate();
        this.name = name;
        this.code = code;
        this.rows = rows;
    }

    private ReservedSection() {

    }

    @Override
    public SectionId id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String code() {
        return code;
    }

    public List<Row> rows() {
        return rows;
    }

}
