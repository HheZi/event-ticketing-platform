package com.ddd.event_ticketing_platform.users.domain.model;

import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.Instant;

@MappedSuperclass
public abstract class User extends AbstractAggregateRoot<User> {

    private String name;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    public User(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}
