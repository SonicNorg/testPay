package com.axel.testpay.cron;

import com.axel.testpay.model.Payment;
import com.axel.testpay.model.State;
import com.axel.testpay.model.WeebHookResponse;
import com.axel.testpay.service.PaymentService;
import com.axel.testpay.service.PaymentVerifyerService;
import com.axel.testpay.service.ResendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * This is a trigger class, which simulate a real receiving event and call webhook method.
 * For demonstration, it use cron, but in real situation webhook method should be called after receiving data from another system.
 */

@Component
public class WebhookTrigerer {

    private final PaymentService paymentService;

    private final PaymentVerifyerService paymentVerifyerService;

    private final ResendService resendService;

    final Random random;

    @Autowired
    public WebhookTrigerer(PaymentService paymentService, PaymentVerifyerService paymentVerifyerService, ResendService resendService) {
        this.paymentService = paymentService;
        this.paymentVerifyerService = paymentVerifyerService;
        this.resendService = resendService;
        this.random = new Random();
    }

//    @Scheduled(fixedDelay = 10368000L) for real 25 times in 3 days.
    @Scheduled(fixedDelay = 10L) //for demo or testing
    private void trigger() {
        List<Payment> createdPayments = paymentService.getCreatedPayments();
        for (Payment payment : createdPayments) {
            int random = this.random.nextInt(99);
//            if (random<99) { for tests & demo
            // only for demo, smth like random, info about payment comes or not.
            if (random<50) {
                paymentVerifyerService.sendVerifyMessage(payment).thenApply(result -> {
                    WeebHookResponse whr = (WeebHookResponse) result;
                    if (whr.isSuccess()) {
                        resendService.deletePaymentFromResend(payment);
                        payment.setState(State.approved);
                        paymentService.updatePayment(payment);
                        return payment;
                    } else {
                        return whr;
                    }
                });
            }
        }
    }
}
