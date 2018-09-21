package com.axel.testpay.service.impl;

import com.axel.testpay.model.Payment;
import com.axel.testpay.model.State;
import com.axel.testpay.model.WebHookObject;
import com.axel.testpay.model.WeebHookResponse;
import com.axel.testpay.service.PaymentService;
import com.axel.testpay.service.PaymentVerifyerService;
import com.axel.testpay.service.ResendService;
import com.axel.testpay.service.WebHookObjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class PaymentVerifyerServiceImpl implements PaymentVerifyerService {

    private static final Logger log = LoggerFactory.getLogger(PaymentVerifyerServiceImpl.class);

    private final RestTemplate restTemplate;

    private final PaymentService paymentService;

    private final ResendService resendService;

    private final WebHookObjectService webHookObjectService;

    @Autowired
    public PaymentVerifyerServiceImpl(RestTemplateBuilder RestTemplateBuilder,
                                      PaymentService paymentService,
                                      ResendService resendService,
                                      WebHookObjectService webHookObjectService) {
        this.restTemplate = RestTemplateBuilder.build();
        this.paymentService = paymentService;
        this.resendService = resendService;
        this.webHookObjectService = webHookObjectService;
    }

    @Override
    @Async
    public CompletableFuture<Object> sendVerifyMessage(Payment payment) {
        try {
            String notificationUrl = payment.getNotificationUrl();
            WebHookObject object = webHookObjectService.createObject(payment);
            HttpEntity<WebHookObject> request = new HttpEntity<>(object);
            ObjectMapper objectMapper = new ObjectMapper();
            Object result = restTemplate.postForObject(notificationUrl, objectMapper.writeValueAsString(request), Object.class);
            //result of request is fake here, coz this doesn't matter, we or have result, or have exception if request unsuccess.
            WeebHookResponse response = new WeebHookResponse();
            if (result != null) {
                response.setSuccess(true);
            } else {
                response.setSuccess(false);
            }
            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            if (resendService.isPaymentOnResend(payment)) {
                int count = resendService.getResendCountForPayment(payment);
                if (count >= 25) {
                    log.info("Payment with id:" + payment.getId() + " is resended over 25 times.");
                    resendService.deletePaymentFromResend(payment);
                    payment.setState(State.failed);
                    paymentService.updatePayment(payment);
                    log.info("Setting status 'failed' for payment" + payment.getId());
                } else {
                    resendService.incrementCountForPayment(payment);
                }
            } else {
                resendService.addPaymentToResend(payment);
                log.info("Payment with id: " + payment.getId() + " adding to resend.");
            }
        }
        WeebHookResponse response = new WeebHookResponse();
        response.setSuccess(false);
        return CompletableFuture.completedFuture(response);
    }
}
