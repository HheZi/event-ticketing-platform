package com.ddd.event_ticketing_platform.users.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.jmolecules.ddd.annotation.Identity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
public class Admin extends User {

    @Identity
    @EmbeddedId
    private AdminId id;

    public Admin(AdminId id, String username, String password) {
        super(username, password);
        this.id = id;
    }

    private Admin() {
        super();
    }

    public AdminId id() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));
    }

}
