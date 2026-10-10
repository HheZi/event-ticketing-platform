package com.ddd.event_ticketing_platform.catalog.application;

import com.ddd.event_ticketing_platform.catalog.domain.event.enums.SessionStatus;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.Session;
import com.ddd.event_ticketing_platform.catalog.domain.event.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SessionSaleStart {

    private static final Logger log = LoggerFactory.getLogger(SessionSaleStart.class);

    private final SessionRepository sessions;

    public SessionSaleStart(SessionRepository sessions) {
        this.sessions = sessions;
    }

    // Would be better to use Quarts or JobRun frameworks
    @Scheduled(fixedDelay = 10L, timeUnit = TimeUnit.SECONDS)
    public void startSessionsSale() {
        List<Session> publishedSessions = sessions.findByStatus(SessionStatus.PUBLISHED);

        Instant now = Instant.now();

        List<Session> sessionsToStartSales = publishedSessions.stream()
                .filter(session -> session.salesStart().isBefore(now))
                .toList();

        for (Session session : sessionsToStartSales) {
            try {
                session.startSale();
            } catch (Exception e) {
                log.warn("Error has occurred while start sale for session {}", session.id(), e);
            }
        }

        sessions.saveAll(sessionsToStartSales);
    }

}
