package com.axel.testpay.dao;

import com.axel.testpay.model.Payment;

import java.util.List;

public interface PaymentDao {
    void createPayment(Payment payment);
    void updatePayment(Payment payment);
    void deletePayment(Payment payment);
    Payment getPaymentById(int id);
    List<Payment> getCreatedPayments();
}
