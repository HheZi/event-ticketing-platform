package com.ddd.event_ticketing_platform.users.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.jmolecules.ddd.annotation.Identity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
public class Organizer extends User {

    @Identity
    @EmbeddedId
    private OrganizerId id;

    public Organizer(OrganizerId id, String username, String password) {
        super(username, password);
        this.id = id;
    }

    private Organizer() {
        super();
    }

    public OrganizerId id() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ORGANIZER"));
    }

}
