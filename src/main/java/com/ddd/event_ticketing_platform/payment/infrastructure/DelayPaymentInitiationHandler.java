package com.ddd.event_ticketing_platform.payment.infrastructure;

import com.ddd.event_ticketing_platform.payment.application.PaymentInitiationHandler;
import com.ddd.event_ticketing_platform.payment.domain.exception.PaymentDeclined;
import com.ddd.event_ticketing_platform.payment.domain.exception.PaymentFailed;
import com.ddd.event_ticketing_platform.payment.domain.model.Payment;
import com.ddd.event_ticketing_platform.payment.domain.model.PaymentDetailsId;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

@Component
@ConditionalOnProperty(prefix = "app.payment", name = "stub", havingValue = "delay")
public class DelayPaymentInitiationHandler implements PaymentInitiationHandler {

    private static final Logger log = LoggerFactory.getLogger(DelayPaymentInitiationHandler.class);

    private static final Random RANDOM = new Random();

    private static final int MAX_DELAY = 5;

    @PostConstruct
    public void postConstruct() {
        log.info("Using DelayPaymentInitiationHandler as implementation for PaymentInitiationHandler");
    }

    @Override
    public PaymentDetailsId handle(Payment payment) throws PaymentDeclined, PaymentFailed {
        try {
            Thread.sleep(Duration.ofSeconds(RANDOM.nextInt(MAX_DELAY + 1)).toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new PaymentDetailsId(UUID.randomUUID());
    }

}
