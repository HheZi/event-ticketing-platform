package com.ddd.event_ticketing_platform.catalog.domain.event.repository;

import com.ddd.event_ticketing_platform.catalog.domain.event.model.Event;
import com.ddd.event_ticketing_platform.catalog.domain.event.model.EventId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, EventId> {
}
