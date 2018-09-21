package com.axel.testpay.service;

import com.axel.testpay.model.Payment;

import java.util.concurrent.CompletableFuture;

public interface PaymentVerifyerService {
    CompletableFuture<Object> sendVerifyMessage(Payment payment);
}
