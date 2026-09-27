package com.ddd.event_ticketing_platform.users.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
class Organizer extends User {

    @Identity
    @EmbeddedId
    private final OrganizerId id;

    public Organizer(OrganizerId id, String name) {
        super(name);
        this.id = id;
    }

    public OrganizerId id() {
        return id;
    }
}
