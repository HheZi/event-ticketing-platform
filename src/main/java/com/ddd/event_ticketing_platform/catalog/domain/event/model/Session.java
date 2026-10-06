package com.ddd.event_ticketing_platform.catalog.domain.event.model;

import com.ddd.event_ticketing_platform.catalog.domain.event.enums.SessionStatus;
import com.ddd.event_ticketing_platform.catalog.domain.event.event.SessionPublished;
import com.ddd.event_ticketing_platform.catalog.domain.event.exception.CannotPublicsSessionException;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatingLayoutId;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.VenueId;
import jakarta.persistence.*;
import org.jmolecules.ddd.annotation.Identity;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.Instant;
import java.util.List;

@Entity
public class Session extends AbstractAggregateRoot<Session> {

    @Identity
    @EmbeddedId
    private SessionId id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Event event;
    @Embedded
    private VenueId venueId;
    @Embedded
    private SeatingLayoutId seatingLayoutId;
    private Instant startTime, salesStart, salesEnd;
    @Enumerated(EnumType.STRING)
    private SessionStatus status;
    @OneToMany(mappedBy = "session", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<TicketType> ticketTypes;

    public Session(
            Event event, VenueId venueId, SeatingLayoutId seatingLayoutId,
            Instant startTime, Instant salesStart, Instant salesEnd,
            List<TicketType> ticketTypes
    ) {
        this.event = event;
        this.venueId = venueId;
        this.seatingLayoutId = seatingLayoutId;
        this.startTime = startTime;
        this.salesStart = salesStart;
        this.salesEnd = salesEnd;
        this.status = SessionStatus.DRAFT;
        this.ticketTypes = ticketTypes;

        this.ticketTypes.forEach(ticketType -> ticketType.setSession(this));
    }

    private Session() {

    }

    public void publish() {
        if (this.status != SessionStatus.DRAFT) {
            throw new CannotPublicsSessionException("Session is not draft");
        }

        if (ticketTypes.isEmpty()) {
            throw new CannotPublicsSessionException("Ticket types are empty");
        }

        this.status = SessionStatus.PUBLISHED;

        registerEvent(new SessionPublished(this.id));
    }

    public SessionId id() {
        return id;
    }

    public VenueId venueId() {
        return venueId;
    }

    public SeatingLayoutId seatingLayoutId() {
        return seatingLayoutId;
    }

    public Instant startTime() {
        return startTime;
    }

    public Instant salesStart() {
        return salesStart;
    }

    public Instant salesEnd() {
        return salesEnd;
    }

    public SessionStatus status() {
        return status;
    }

    public List<TicketType> getTicketTypes() {
        return ticketTypes;
    }

}
