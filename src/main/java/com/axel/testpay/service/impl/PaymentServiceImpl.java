package com.axel.testpay.service.impl;

import com.axel.testpay.dao.AmountDao;
import com.axel.testpay.dao.PayerDao;
import com.axel.testpay.dao.PaymentDao;
import com.axel.testpay.dao.TransactionDao;
import com.axel.testpay.model.Payment;
import com.axel.testpay.model.State;
import com.axel.testpay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private PayerDao payerDao;

    @Autowired
    private AmountDao amountDao;

    @Transactional
    public boolean acceptPayment(Payment payment) {
        try {
            payerDao.createPayer(payment.getPayer());
            amountDao.createAmount(payment.getTransaction().getAmount());
            transactionDao.createTransaction(payment.getTransaction());
            payment.setState(State.created);
            paymentDao.createPayment(payment);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    @Override
    public List<Payment> getCreatedPayments() {
        return paymentDao.getCreatedPayments();
    }

    @Override
    public void updatePayment(Payment payment) {
        paymentDao.updatePayment(payment);
    }
}
