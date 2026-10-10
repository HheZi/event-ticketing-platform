package com.ddd.event_ticketing_platform.catalog.domain.event.model;

import com.ddd.event_ticketing_platform.catalog.domain.event.enums.SessionStatus;
import com.ddd.event_ticketing_platform.catalog.domain.event.event.SessionCancelled;
import com.ddd.event_ticketing_platform.catalog.domain.event.event.SessionOnSale;
import com.ddd.event_ticketing_platform.catalog.domain.event.event.SessionPublished;
import com.ddd.event_ticketing_platform.catalog.domain.event.exception.CannotCancelSession;
import com.ddd.event_ticketing_platform.catalog.domain.event.exception.CannotPublishSession;
import com.ddd.event_ticketing_platform.catalog.domain.event.exception.CannotStartSales;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.SeatingLayoutId;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.VenueId;
import jakarta.persistence.*;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.Instant;
import java.util.List;

@Entity
@AggregateRoot
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
            VenueId venueId, SeatingLayoutId seatingLayoutId,
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
            throw new CannotPublishSession("Session is not draft");
        }

        if (ticketTypes.isEmpty()) {
            throw new CannotPublishSession("Ticket types are empty");
        }

        this.status = SessionStatus.PUBLISHED;

        registerEvent(new SessionPublished(id()));
    }

    public void cancel() {
        if (this.status == SessionStatus.COMPLETED) {
            throw new CannotCancelSession("Status of session is completed");
        }

        this.status = SessionStatus.CANCELLED;

        registerEvent(new SessionCancelled(id()));
    }

    public void startSale() {
        if (this.status != SessionStatus.PUBLISHED) {
            throw new CannotStartSales("Session is not published");
        }

        this.status = SessionStatus.ON_SALE;

        registerEvent(new SessionOnSale(id()));
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

    protected void setEvent(Event event) {
        this.event = event;
    }

}
