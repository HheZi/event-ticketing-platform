package com.ddd.event_ticketing_platform.catalog.domain.venue.model;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public sealed interface Section permits ReservedSection, GeneralAdmissionSection {

    SectionId id();

    String name();

    String code();

}
