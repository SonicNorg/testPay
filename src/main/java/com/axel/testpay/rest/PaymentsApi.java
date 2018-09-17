package com.axel.testpay.rest;

import com.axel.testpay.dao.AmountDao;
import com.axel.testpay.dao.PayerDao;
import com.axel.testpay.dao.PaymentDao;
import com.axel.testpay.dao.TransactionDao;
import com.axel.testpay.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentsApi {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private AmountDao amountDao;

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private PayerDao payerDao;

    @PostMapping(path = "/payments/payment", consumes = "application/json")
    public Object addPayment(
            @RequestBody Payment payment
    ) {
//        Amount amount = new Amount();
//        amount.setCurrency("USD");
//        amount.setValue("225.5");
//        amountDao.createAmount(amount);
//        Transaction transaction = new Transaction();
//        transaction.setAmount(amount);
//        transaction.setExternalId("test ext id");
//        transaction.setDescription("test dessc");
//        transaction.setId(142);
//        transactionDao.createTransaction(transaction);
//        Transaction transactionById = transactionDao.getTransactionById(142);
//        Payer payer = new Payer();
//        payer.setEmail("test@gmail.com");
//        payerDao.createPayer(payer);
//        Payment payment = new Payment();
//        payment.setPayer(payer);
//        payment.setTransaction(transaction);
//        payment.setIntent(Intent.order);
//        payment.setNotificationUrl("test");
//        paymentDao.createPayment(payment);
//        paymentDao.getPaymentById(0);
        return 1;
    }
}
