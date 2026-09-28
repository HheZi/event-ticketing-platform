package com.ddd.event_ticketing_platform.users.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;

@MappedSuperclass
public abstract class User implements UserDetails {

    @Column(unique = true)
    private String username;
    private String password;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected User() {
    }

    public String name() {
        return username;
    }

    public String password() {
        return password;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
