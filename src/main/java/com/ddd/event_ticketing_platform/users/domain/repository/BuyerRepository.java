package com.ddd.event_ticketing_platform.users.domain.repository;

import com.ddd.event_ticketing_platform.users.domain.model.Buyer;
import com.ddd.event_ticketing_platform.users.domain.model.BuyerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, BuyerId> {

    Buyer findByUsername(String username);

}
