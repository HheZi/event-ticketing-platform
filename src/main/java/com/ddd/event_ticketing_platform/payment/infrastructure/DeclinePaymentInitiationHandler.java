package com.ddd.event_ticketing_platform.payment.infrastructure;

import com.ddd.event_ticketing_platform.payment.application.PaymentInitiationHandler;
import com.ddd.event_ticketing_platform.payment.domain.exception.PaymentDeclined;
import com.ddd.event_ticketing_platform.payment.domain.model.Payment;
import com.ddd.event_ticketing_platform.payment.domain.model.PaymentDetailsId;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "app.payment", name = "stub", havingValue = "decline")
public class DeclinePaymentInitiationHandler implements PaymentInitiationHandler {

    private static final Logger log = LoggerFactory.getLogger(DeclinePaymentInitiationHandler.class);

    @PostConstruct
    public void postConstruct() {
        log.info("Using DeclinePaymentInitiationHandler as implementation for PaymentInitiationHandler");
    }

    @Override
    public PaymentDetailsId handle(Payment payment) {
        throw new PaymentDeclined();
    }

}
