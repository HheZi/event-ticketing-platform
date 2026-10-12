package com.ddd.event_ticketing_platform.catalog.application;

import com.ddd.event_ticketing_platform.catalog.application.query.TicketTypeRule;
import com.ddd.event_ticketing_platform.catalog.domain.event.exception.SessionNotFound;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.Session;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.SessionId;
import com.ddd.event_ticketing_platform.catalog.domain.event.repository.SessionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessionQuery {

    private final SessionRepository sessions;

    public SessionQuery(SessionRepository sessions) {
        this.sessions = sessions;
    }

    public List<TicketTypeRule> getTicketTypeRules(SessionId sessionId) {
        Session session = getSession(sessionId);

        return session.getTicketTypes().stream()
                .map(TicketTypeRule::from)
                .collect(Collectors.toList());
    }

    private Session getSession(SessionId sessionId) {
        return sessions.findById(sessionId).orElseThrow(() -> new SessionNotFound(sessionId));
    }
}
