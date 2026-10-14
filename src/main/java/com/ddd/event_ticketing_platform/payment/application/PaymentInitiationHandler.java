package com.ddd.event_ticketing_platform.payment.application;

import com.ddd.event_ticketing_platform.payment.domain.exception.PaymentDeclined;
import com.ddd.event_ticketing_platform.payment.domain.exception.PaymentFailed;
import com.ddd.event_ticketing_platform.payment.domain.model.Payment;
import com.ddd.event_ticketing_platform.payment.domain.model.PaymentDetailsId;

public interface PaymentInitiationHandler {

    PaymentDetailsId handle(Payment payment) throws PaymentDeclined, PaymentFailed;

}
