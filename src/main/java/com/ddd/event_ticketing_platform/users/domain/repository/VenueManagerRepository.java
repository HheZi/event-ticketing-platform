package com.ddd.event_ticketing_platform.users.domain.repository;

import com.ddd.event_ticketing_platform.users.domain.model.VenueManager;
import com.ddd.event_ticketing_platform.users.domain.model.VenueManagerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueManagerRepository extends JpaRepository<VenueManager, VenueManagerId> {

    VenueManager findByUsername(String username);

}
