package com.axel.testpay.mapper;

import com.axel.testpay.dao.PayerDao;
import com.axel.testpay.dao.TransactionDao;
import com.axel.testpay.model.Intent;
import com.axel.testpay.model.Payer;
import com.axel.testpay.model.Payment;
import com.axel.testpay.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PaymentMapper implements RowMapper<Payment> {

    private final PayerDao payerDao;

    private final TransactionDao transactionDao;

    @Autowired
    public PaymentMapper(PayerDao payerDao, TransactionDao transactionDao) {
        this.payerDao = payerDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public Payment mapRow(ResultSet resultSet, int i) throws SQLException {
        Payment payment = new Payment();
        payment.setId(resultSet.getInt("id"));
        payment.setIntent(Intent.valueOf(resultSet.getString("intent")));
        payment.setNotificationUrl(resultSet.getString("notification_url"));
        int payer_id = resultSet.getInt("payer_id");
        Payer payer = payerDao.getPayerById(payer_id);
        payment.setPayer(payer);
        int transaction_id = resultSet.getInt("transaction_id");
        Transaction transaction = transactionDao.getTransactionById(transaction_id);
        payment.setTransaction(transaction);
        return payment;
    }
}
