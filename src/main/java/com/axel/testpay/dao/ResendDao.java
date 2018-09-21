package com.axel.testpay.dao;

import com.axel.testpay.model.ResendedPayment;

public interface ResendDao {
    void createResend(ResendedPayment payment);
    void updateResend(ResendedPayment payment);
    void deleteResend(ResendedPayment payment);
    ResendedPayment getResendById(int id);
    boolean isExistResend(int id);
}
