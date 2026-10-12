package com.ddd.event_ticketing_platform.ordering.domain.repository;

import com.ddd.event_ticketing_platform.ordering.domain.model.Cart;
import com.ddd.event_ticketing_platform.ordering.domain.model.CartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartId> {
}
