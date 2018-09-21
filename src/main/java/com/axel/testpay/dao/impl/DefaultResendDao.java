package com.axel.testpay.dao.impl;

import com.axel.testpay.dao.PaymentDao;
import com.axel.testpay.dao.ResendDao;
import com.axel.testpay.mapper.ResendMapper;
import com.axel.testpay.model.ResendedPayment;
import com.axel.testpay.util.QuerryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultResendDao implements ResendDao {

    private final JdbcTemplate jdbcTemplate;

    private final PaymentDao paymentDao;

    @Autowired
    public DefaultResendDao(JdbcTemplate jdbcTemplate, PaymentDao paymentDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.paymentDao = paymentDao;
    }

    @Override
    public void createResend(ResendedPayment payment) {
        String sql = "INSERT INTO resended_payment (id, id_payment, resend_count) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, payment.getId(), payment.getPayment().getId(), payment.getResendCount());
    }

    @Override
    public void updateResend(ResendedPayment payment) {
        String sql = "UPDATE resended_payment SET id_payment =?, resend_count=? WHERE id = ?";
        jdbcTemplate.update(sql, payment.getPayment().getId(), payment.getResendCount(), payment.getId());
    }

    @Override
    public void deleteResend(ResendedPayment payment) {
        String sql = QuerryUtils.getDeleteQueryForEntity("resended_payment", payment.getId());
        jdbcTemplate.update(sql);
    }

    @Override
    public ResendedPayment getResendById(int id) {
        String sql = QuerryUtils.getSelectQueryForEntity("resended_payment", id);
        RowMapper<ResendedPayment> rowMapper = new ResendMapper(paymentDao);
        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    @Override
    public boolean isExistResend(int paymentId) {
        String sql = "SELECT count(*) FROM resended_payment WHERE id_payment=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, paymentId);
        return count != 0;
    }
}
