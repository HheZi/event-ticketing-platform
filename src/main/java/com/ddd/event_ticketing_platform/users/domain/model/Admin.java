package com.ddd.event_ticketing_platform.users.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public class Admin extends User {

    @Identity
    @EmbeddedId
    private final AdminId id;

    public Admin(AdminId id, String name) {
        super(name);
        this.id = id;
    }

    public AdminId id() {
        return id;
    }
}
