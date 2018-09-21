package com.axel.testpay.service;

import com.axel.testpay.model.Payment;

public interface ResendService {
    void addPaymentToResend(Payment payment);
    int getResendCountForPayment(Payment payment);
    void deletePaymentFromResend(Payment payment);
    void incrementCountForPayment(Payment payment);
    boolean isPaymentOnResend(Payment payment);
}
