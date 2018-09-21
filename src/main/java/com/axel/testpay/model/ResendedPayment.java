package com.axel.testpay.model;

public class ResendedPayment extends AbstractEntity {
    private Payment payment;
    private int resendCount;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getResendCount() {
        return resendCount;
    }

    public void setResendCount(int resendCount) {
        this.resendCount = resendCount;
    }

    public ResendedPayment() {
    }
}
