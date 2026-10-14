package com.ddd.event_ticketing_platform.payment.infrastructure;

import com.ddd.event_ticketing_platform.payment.application.PaymentInitiationHandler;
import com.ddd.event_ticketing_platform.payment.domain.model.Payment;
import com.ddd.event_ticketing_platform.payment.domain.model.PaymentDetailsId;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConditionalOnProperty(prefix = "app.payment", name = "stub", havingValue = "success")
public class SuccessPaymentInitiationHandler implements PaymentInitiationHandler {

    private static final Logger log = LoggerFactory.getLogger(SuccessPaymentInitiationHandler.class);

    @PostConstruct
    public void postConstruct() {
        log.info("Using SuccessPaymentInitiationHandler as implementation for PaymentInitiationHandler");
    }

    @Override
    public PaymentDetailsId handle(Payment payment) {
        return new PaymentDetailsId(UUID.randomUUID());
    }

}
