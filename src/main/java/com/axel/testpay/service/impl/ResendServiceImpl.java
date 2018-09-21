package com.axel.testpay.service.impl;

import com.axel.testpay.dao.ResendDao;
import com.axel.testpay.model.Payment;
import com.axel.testpay.model.ResendedPayment;
import com.axel.testpay.service.ResendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResendServiceImpl implements ResendService {

    private final ResendDao resendDao;

    @Autowired
    public ResendServiceImpl(ResendDao resendDao) {
        this.resendDao = resendDao;
    }


    @Override
    public void addPaymentToResend(Payment payment) {
        ResendedPayment resendedPayment = new ResendedPayment();
        resendedPayment.setPayment(payment);
        resendedPayment.setResendCount(1);
        resendDao.createResend(resendedPayment);
    }

    @Override
    public int getResendCountForPayment(Payment payment) {
        ResendedPayment resendById = resendDao.getResendById(payment.getId());
        if (resendById == null) {
            return 0;
        } else {
            return resendById.getResendCount();
        }
    }

    @Override
    public void deletePaymentFromResend(Payment payment) {
        ResendedPayment resendById = resendDao.getResendById(payment.getId());
        if (resendById != null) {
            resendDao.deleteResend(resendById);
        }
    }

    @Override
    public void incrementCountForPayment(Payment payment) {
        ResendedPayment resendById = resendDao.getResendById(payment.getId());
        if (resendById != null) {
            resendById.setResendCount(resendById.getResendCount() + 1);
            resendDao.updateResend(resendById);
        }
    }

    @Override
    public boolean isPaymentOnResend(Payment payment) {
        return resendDao.isExistResend(payment.getId());
    }
}
