package com.ddd.event_ticketing_platform.payment.application;

import com.ddd.event_ticketing_platform.payment.domain.event.PaymentInitiated;
import com.ddd.event_ticketing_platform.payment.domain.event.RefundIssued;
import com.ddd.event_ticketing_platform.payment.domain.exception.PaymentDeclined;
import com.ddd.event_ticketing_platform.payment.domain.exception.PaymentFailed;
import com.ddd.event_ticketing_platform.payment.domain.exception.PaymentNotFound;
import com.ddd.event_ticketing_platform.payment.domain.model.Payment;
import com.ddd.event_ticketing_platform.payment.domain.model.PaymentDetailsId;
import com.ddd.event_ticketing_platform.payment.domain.model.PaymentId;
import com.ddd.event_ticketing_platform.payment.domain.repository.PaymentRepository;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventHandler {

    private final PaymentRepository payments;

    private final PaymentInitiationHandler paymentInitiationHandler;

    public PaymentEventHandler(PaymentRepository payments, PaymentInitiationHandler paymentInitiationHandler) {
        this.payments = payments;
        this.paymentInitiationHandler = paymentInitiationHandler;
    }

    @ApplicationModuleListener
    public void paymentInitiated(PaymentInitiated paymentInitiated) {
        PaymentId paymentId = paymentInitiated.paymentId();

        Payment payment = getPayment(paymentId);

        try {
            PaymentDetailsId detailId = paymentInitiationHandler.handle(payment);

            payment.capture(detailId);
        } catch (PaymentFailed e) {
            payment.fail();
        } catch (PaymentDeclined e) {
            payment.decline();
        }

        payments.save(payment);
    }

    @ApplicationModuleListener
    public void refundIssued(RefundIssued refundIssued) {
        Payment payment = getPayment(refundIssued.paymentId());

        // Simply refund. Makes life easier.
        payment.refund();

        payments.save(payment);
    }

    private Payment getPayment(PaymentId paymentId) {
        return payments.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFound(paymentId));
    }

}
