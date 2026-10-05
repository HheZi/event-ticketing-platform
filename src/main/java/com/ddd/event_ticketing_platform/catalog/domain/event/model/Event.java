package com.ddd.event_ticketing_platform.catalog.domain.event.model;

import com.ddd.event_ticketing_platform.catalog.domain.event.enums.SessionStatus;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatingLayoutId;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.VenueId;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@AggregateRoot
public class Event extends AbstractAggregateRoot<Event> {

    @Identity
    @EmbeddedId
    private EventId id;
    private String name;
    private String description;
    private String category;
    @OneToMany(mappedBy = "event",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> sessions = new ArrayList<>();

    public Event(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    private Event() {

    }

    public void addSession(
            VenueId venueId, SeatingLayoutId seatingLayoutId,
            Instant startTime, Instant salesStart, Instant salesEnd,
            List<TicketType> ticketTypes
    ) {
        sessions.add(new Session(
                this, venueId, seatingLayoutId,
                startTime, salesStart, salesEnd, ticketTypes
        ));
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

    public List<Session> sessions() {
        return sessions;
    }

}
