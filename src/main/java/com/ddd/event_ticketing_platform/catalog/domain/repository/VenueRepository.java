package com.ddd.event_ticketing_platform.catalog.domain.repository;

import com.ddd.event_ticketing_platform.catalog.domain.model.Venue;
import com.ddd.event_ticketing_platform.catalog.domain.model.VenueId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue, VenueId> {
}
