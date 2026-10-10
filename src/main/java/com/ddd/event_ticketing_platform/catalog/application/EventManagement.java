package com.ddd.event_ticketing_platform.catalog.application;

import com.ddd.event_ticketing_platform.catalog.application.command.AddSessionToEventCommand;
import com.ddd.event_ticketing_platform.catalog.application.command.CreateEventCommand;
import com.ddd.event_ticketing_platform.catalog.domain.event.exception.EventNotFound;
import com.ddd.event_ticketing_platform.catalog.domain.event.exception.SessionNotFound;
import com.ddd.event_ticketing_platform.catalog.domain.event.exception.TicketsNotCoveringsAllSections;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.Event;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.EventId;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.Session;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.SessionId;
import com.ddd.event_ticketing_platform.catalog.domain.event.repository.EventRepository;
import com.ddd.event_ticketing_platform.catalog.domain.event.repository.SessionRepository;
import com.ddd.event_ticketing_platform.catalog.domain.venue.exception.VenueNotFound;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.*;
import com.ddd.event_ticketing_platform.catalog.domain.venue.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventManagement {

    private final EventRepository events;
    private final VenueRepository venues;
    private final SessionRepository sessions;

    public EventManagement(EventRepository events, VenueRepository venues, SessionRepository sessions) {
        this.events = events;
        this.venues = venues;
        this.sessions = sessions;
    }

    @Transactional
    public EventId createEvent(CreateEventCommand command) {
        Event event = new Event(command.name(), command.description(), command.category());

        event = events.save(event);

        return event.id();
    }

    @Transactional
    public SessionId addSessionToEvent(EventId eventId, AddSessionToEventCommand command) {
        Event event = getEvent(eventId);

        verifyVenue(command);

        Session session = command.toSession();

        event.addSession(session);

        session = sessions.save(session);

        return session.id();
    }

    @Transactional
    public SessionId publishSession(SessionId sessionId) {
        Session session = getSession(sessionId);

        session.publish();

        sessions.save(session);

        return sessionId;
    }

    @Transactional
    public SessionId cancelSession(SessionId sessionId) {
        Session session = getSession(sessionId);

        session.cancel();

        sessions.save(session);

        return sessionId;
    }

    private void verifyVenue(AddSessionToEventCommand command) {
        VenueId venueId = command.venueId();

        Venue venue = venues
                .findById(venueId).orElseThrow(() -> new VenueNotFound(venueId));

        SeatingLayout seatingLayout = venue.seatingLayout(command.seatingLayoutId());

        Set<SectionId> sectionIds = seatingLayout.sections()
                .stream().map(Section::id)
                .collect(Collectors.toSet());

        boolean areTicketsCoverAllSections = command.tickets().stream().map(AddSessionToEventCommand.TicketTypeInfo::sectionId)
                .allMatch(sectionIds::contains);

        if (!areTicketsCoverAllSections) {
            throw new TicketsNotCoveringsAllSections();
        }
    }

    private Event getEvent(EventId eventId) {
        return events.findById(eventId).orElseThrow(() -> new EventNotFound(eventId));
    }

    private Session getSession(SessionId sessionId) {
        return sessions.findById(sessionId).orElseThrow(() -> new SessionNotFound(sessionId));
    }

}
