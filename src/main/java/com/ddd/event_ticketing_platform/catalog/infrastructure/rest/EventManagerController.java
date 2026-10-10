package com.ddd.event_ticketing_platform.catalog.infrastructure.rest;

import com.ddd.event_ticketing_platform.catalog.application.EventManagement;
import com.ddd.event_ticketing_platform.catalog.application.command.CreateEventCommand;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.EventId;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.SessionId;
import com.ddd.event_ticketing_platform.catalog.infrastructure.rest.dto.request.AddSessionRequest;
import com.ddd.event_ticketing_platform.catalog.infrastructure.rest.dto.request.CreateEventRequest;
import com.ddd.event_ticketing_platform.catalog.infrastructure.rest.dto.response.EventIdResponse;
import com.ddd.event_ticketing_platform.catalog.infrastructure.rest.dto.response.SessionIdResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/organizer/events")
public class EventManagerController {

    private final EventManagement eventManagement;

    public EventManagerController(EventManagement eventManagement) {
        this.eventManagement = eventManagement;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventIdResponse createEvent(
            @Valid @RequestBody CreateEventRequest request
    ) {
        EventId eventId =
                eventManagement.createEvent(new CreateEventCommand(request.getName(), request.getDescription(), request.getCategory()));

        return new EventIdResponse(eventId.eventId());
    }

    @PutMapping("/{eventId}")
    public SessionIdResponse addSession(
            @PathVariable("eventId") Long eventId,
            @Valid @RequestBody AddSessionRequest request
    ) {
        SessionId id =
                eventManagement.addSessionToEvent(new EventId(eventId), request.toCommand());

        return new SessionIdResponse(id.sessionId());
    }

    @PutMapping("/{eventId}/sessions/{sessionId}/publish")
    public SessionId publishSession(
            @PathVariable("eventId") Long eventId,
            @PathVariable("sessionId") Long sessionId
    ) {
        SessionId id =
                eventManagement.publishSession(new SessionId(sessionId));

        return new SessionId(id.sessionId());
    }

    @PutMapping("/{eventId}/sessions/{sessionId}/cancel")
    public SessionId cancelSession(
            @PathVariable("eventId") Long eventId,
            @PathVariable("sessionId") Long sessionId
    ) {
        SessionId id =
                eventManagement.cancelSession(new SessionId(sessionId));

        return new SessionId(id.sessionId());
    }

}
