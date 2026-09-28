package com.ddd.event_ticketing_platform.users.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import org.jmolecules.ddd.annotation.Identity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
public class Buyer extends User {

    @Identity
    @EmbeddedId
    private BuyerId id;

    public Buyer(String username, String password) {
        super(username, password);
    }

    private Buyer() {
        super();
    }

    public BuyerId id() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("BUYER"));
    }

}
