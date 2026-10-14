package com.ddd.event_ticketing_platform.payment.domain.repository;

import com.ddd.event_ticketing_platform.payment.domain.model.Payment;
import com.ddd.event_ticketing_platform.payment.domain.model.PaymentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, PaymentId> {
}
