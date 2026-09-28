package com.ddd.event_ticketing_platform.users.domain.repository;

import com.ddd.event_ticketing_platform.users.domain.model.Organizer;
import com.ddd.event_ticketing_platform.users.domain.model.OrganizerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, OrganizerId> {

    Organizer findByUsername(String username);

}
