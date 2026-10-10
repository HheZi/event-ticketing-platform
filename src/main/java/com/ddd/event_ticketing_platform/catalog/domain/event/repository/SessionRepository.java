package com.ddd.event_ticketing_platform.catalog.domain.event.repository;

import com.ddd.event_ticketing_platform.catalog.domain.event.enums.SessionStatus;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.Session;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.SessionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, SessionId> {

    List<Session> findByStatus(SessionStatus status);

}
