package com.ddd.event_ticketing_platform.catalog.domain.model;

import com.ddd.event_ticketing_platform.catalog.infrastructure.jpa.SeatingLayoutConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.jmolecules.ddd.annotation.Identity;

import java.util.List;

@Entity
public class Venue {

    @Identity
    @EmbeddedId
    private VenueId id;

    private String name;

    private String address;

    @Column(columnDefinition = "json")
    @Convert(converter = SeatingLayoutConverter.class)
    private List<SeatingLayout> seatingLayouts;

    public Venue(String name, String address) {
        this.name = name;
        this.address = address;
    }

    private Venue() {
    }

    public void addSeatingLayout(SeatingLayout seatingLayout) {
        this.seatingLayouts.add(seatingLayout);
    }

    public VenueId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String address() {
        return address;
    }

    public List<SeatingLayout> seatingLayouts() {
        return seatingLayouts;
    }
}
