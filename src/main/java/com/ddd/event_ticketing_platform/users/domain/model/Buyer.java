package com.ddd.event_ticketing_platform.users.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
public class Buyer extends User {

    @Identity
    @EmbeddedId
    private final BuyerId id;

    public Buyer(BuyerId id, String name) {
        super(name);
        this.id = id;
    }

    public BuyerId id() {
        return id;
    }
}
