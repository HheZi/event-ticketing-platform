package com.ddd.event_ticketing_platform.catalog.domain.venue.repository;

import com.ddd.event_ticketing_platform.catalog.domain.venue.model.Venue;
import com.ddd.event_ticketing_platform.catalog.domain.venue.model.VenueId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue, VenueId> {
}
