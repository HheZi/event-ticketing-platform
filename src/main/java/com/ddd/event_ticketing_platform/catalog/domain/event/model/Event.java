package com.ddd.event_ticketing_platform.catalog.domain.event.model;

import jakarta.persistence.*;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.util.ArrayList;
import java.util.List;

@Entity
@AggregateRoot
public class Event {

    @Identity
    @EmbeddedId
    private EventId id;
    private String name;
    private String description;
    private String category;
    @ElementCollection
    @CollectionTable(name = "event_session", joinColumns = @JoinColumn(name = "event_id"))
    private final List<Long> sessionsIds = new ArrayList<>();

    public Event(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    private Event() {

    }

    public void addSession(Session session) {
        session.setEvent(this);
        sessionsIds.add(session.id().sessionId());
    }

    public EventId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public String category() {
        return category;
    }

    public List<Long> sessionsIds() {
        return sessionsIds;
    }

}
