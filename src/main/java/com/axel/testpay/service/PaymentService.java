package com.axel.testpay.service;

import com.axel.testpay.model.Payment;

import java.util.List;

public interface PaymentService {
    boolean acceptPayment(Payment payment);

    List<Payment> getCreatedPayments();

    void updatePayment(Payment payment);
}
