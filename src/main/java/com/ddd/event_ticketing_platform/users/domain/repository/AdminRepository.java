package com.ddd.event_ticketing_platform.users.domain.repository;

import com.ddd.event_ticketing_platform.users.domain.model.Admin;
import com.ddd.event_ticketing_platform.users.domain.model.AdminId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, AdminId> {

    Admin findByUsername(String username);

}
