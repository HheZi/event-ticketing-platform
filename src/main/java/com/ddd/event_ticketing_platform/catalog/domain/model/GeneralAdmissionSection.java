package com.ddd.event_ticketing_platform.catalog.domain.model;

public non-sealed class GeneralAdmissionSection implements Section {

    private final SectionId id;
    private final String name;
    private final String code;
    private final Integer capacity;

    public GeneralAdmissionSection(String name, String code, Integer capacity) {
        this.id = SectionId.generate();
        this.name = name;
        this.code = code;
        this.capacity = capacity;
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
