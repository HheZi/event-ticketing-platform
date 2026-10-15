package com.ddd.event_ticketing_platform.ordering.domain.repository;

import com.ddd.event_ticketing_platform.ordering.domain.model.Order;
import com.ddd.event_ticketing_platform.ordering.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderId> {
}
