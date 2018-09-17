package com.axel.testpay.dao.impl;

import com.axel.testpay.dao.PayerDao;
import com.axel.testpay.dao.PaymentDao;
import com.axel.testpay.dao.TransactionDao;
import com.axel.testpay.mapper.PaymentMapper;
import com.axel.testpay.model.Intent;
import com.axel.testpay.model.Payment;
import com.axel.testpay.util.QuerryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultPaymentDao implements PaymentDao {

    private final JdbcTemplate jdbcTemplate;

    private final PayerDao payerDao;

    private final TransactionDao transactionDao;

    @Autowired
    public DefaultPaymentDao(JdbcTemplate jdbcTemplate, PayerDao payerDao, TransactionDao transactionDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.payerDao = payerDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public void createPayment(Payment payment) {
        if (payment.getTransaction() == null || payment.getPayer() == null) {
            throw new IllegalStateException("Invalid Payment data");
        }
        String sql = "INSERT INTO payment (id, intent, notification_url, payer_id, transaction_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, payment.getId(), Intent.order.toString(),
                payment.getNotificationUrl(), payment.getPayer().getId(), payment.getTransaction().getId());
    }

    @Override
    public void updatePayment(Payment payment) {
        if (payment.getTransaction() == null || payment.getPayer() == null) {
            throw new IllegalStateException("Invalid Payment data");
        }
        String sql = "UPDATE payment SET intent = ?, notification_url = ?, payer_id = ?, transaction_id = ?";
        jdbcTemplate.update(sql, payment.getIntent(),
                payment.getNotificationUrl(), payment.getPayer().getId(), payment.getTransaction().getId());
    }

    @Override
    public void deletePayment(Payment payment) {
        if (payment.getTransaction() == null || payment.getPayer() == null) {
            throw new IllegalStateException("Invalid Payment data");
        }
        String sql = QuerryUtils.getDeleteQueryForEntity("payment", payment.getId());
        payerDao.deletePayer(payment.getPayer());
        transactionDao.deleteTransaction(payment.getTransaction());
        jdbcTemplate.update(sql);
    }

    @Override
    public Payment getPaymentById(int id) {
        String sql = QuerryUtils.getSelectQueryForEntity("payment", id);
        RowMapper<Payment> rowMapper = new PaymentMapper(payerDao, transactionDao);
        return this.jdbcTemplate.queryForObject(sql, rowMapper);
    }
}
