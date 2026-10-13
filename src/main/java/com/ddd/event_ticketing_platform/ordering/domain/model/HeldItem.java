package com.ddd.event_ticketing_platform.ordering.domain.model;

import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatLabel;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SectionId;
import com.ddd.event_ticketing_platform.catalog.infrastructure.jpa.SeatLabelConverter;
import jakarta.persistence.*;
import org.jmolecules.ddd.annotation.ValueObject;

@Entity
@ValueObject
public class HeldItem {

    @EmbeddedId
    private HeldItemId id;

    @Embedded
    private SectionId sectionId;

    @Convert(converter = SeatLabelConverter.class)
    private SeatLabel seatLabel;

    private Integer capacity;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Hold hold;

    public HeldItem(Integer capacity, SectionId sectionId) {
        this.capacity = capacity;
        this.sectionId = sectionId;
    }

    public HeldItem(SeatLabel seatLabel) {
        this.seatLabel = seatLabel;
    }

    private HeldItem() {
    }

    protected void setHold(Hold hold) {
        this.hold = hold;
    }
}
