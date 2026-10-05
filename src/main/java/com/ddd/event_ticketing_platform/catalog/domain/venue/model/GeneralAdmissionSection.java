package com.ddd.event_ticketing_platform.catalog.domain.venue.model;

public non-sealed class GeneralAdmissionSection implements Section {

    private SectionId id;
    private String name;
    private String code;
    private Integer capacity;

    public GeneralAdmissionSection(String name, String code, Integer capacity) {
        this.id = SectionId.generate();
        this.name = name;
        this.code = code;
        this.capacity = capacity;
    }

    private GeneralAdmissionSection() {

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

    public Integer capacity() {
        return capacity;
    }
}
