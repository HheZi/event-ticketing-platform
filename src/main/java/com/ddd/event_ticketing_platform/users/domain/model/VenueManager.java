package com.ddd.event_ticketing_platform.users.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public class VenueManager extends User {

    @Identity
    @EmbeddedId
    private final VenueManagerId id;

    public VenueManager(VenueManagerId id, String name) {
        super(name);
        this.id = id;
    }

    public VenueManagerId id() {
        return id;
    }
}
