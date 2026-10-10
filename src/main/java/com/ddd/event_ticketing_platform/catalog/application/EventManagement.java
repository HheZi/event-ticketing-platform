package com.ddd.event_ticketing_platform.catalog.application;

import com.ddd.event_ticketing_platform.catalog.application.command.AddSessionToEventCommand;
import com.ddd.event_ticketing_platform.catalog.application.command.CreateEventCommand;
import com.ddd.event_ticketing_platform.catalog.domain.event.exception.EventNotFound;
import com.ddd.event_ticketing_platform.catalog.domain.event.exception.TicketsNotCoveringsAllSections;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.Event;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.EventId;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.SessionId;
import com.ddd.event_ticketing_platform.catalog.domain.event.repository.EventRepository;
import com.ddd.event_ticketing_platform.catalog.domain.venue.exception.VenueNotFound;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.*;
import com.ddd.event_ticketing_platform.catalog.domain.venue.repository.VenueRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventManagement {

    private final EventRepository events;
    private final VenueRepository venues;

    public EventManagement(EventRepository events, VenueRepository venues) {
        this.events = events;
        this.venues = venues;
    }

    @Transactional
    public EventId createEvent(CreateEventCommand command) {
        Event event = new Event(command.name(), command.description(), command.category());

        event = events.save(event);

        return event.id();
    }

    @Transactional
    public EventId addSessionToEvent(EventId eventId, AddSessionToEventCommand command) {
        Event event = getEvent(eventId);

        verifyVenue(command);

        event.addSession(command.venueId(), command.seatingLayoutId(),
                command.startTime(), command.salesStart(), command.salesEnd(),
                command.toTicketTypes()
        );

        events.save(event);

        return eventId;
    }

    @Transactional
    public SessionId publishSession(EventId eventId, SessionId sessionId) {
        Event event = getEvent(eventId);

        event.publishSession(sessionId);

        return sessionId;
    }

    @Transactional
    public SessionId cancelSession(EventId eventId, SessionId sessionId) {
        Event event = getEvent(eventId);

        event.cancelSession(sessionId);

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

    private @NonNull Event getEvent(EventId eventId) {
        return events.findById(eventId).orElseThrow(() -> new EventNotFound(eventId));
    }

}
