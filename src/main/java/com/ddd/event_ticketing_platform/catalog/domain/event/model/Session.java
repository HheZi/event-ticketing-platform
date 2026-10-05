package com.ddd.event_ticketing_platform.catalog.domain.event.model;

import com.ddd.event_ticketing_platform.catalog.domain.event.enums.SessionStatus;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatingLayoutId;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.VenueId;
import jakarta.persistence.*;
import org.jmolecules.ddd.annotation.Identity;

import java.time.Instant;
import java.util.List;

@Entity
public class Session {

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
    }

    private Session() {

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
