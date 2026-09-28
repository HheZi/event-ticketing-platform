package com.ddd.event_ticketing_platform.users.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.jmolecules.ddd.annotation.Identity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
public class VenueManager extends User {

    @Identity
    @EmbeddedId
    private VenueManagerId id;

    public VenueManager(String name, String password) {
        super(name, password);
    }

    private VenueManager() {
        super();
    }

    public VenueManagerId id() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("VENUE_MANAGER"));

    }

}
