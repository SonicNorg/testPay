package com.axel.testpay.mapper;

import com.axel.testpay.dao.PaymentDao;
import com.axel.testpay.model.Payment;
import com.axel.testpay.model.ResendedPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class ResendMapper implements RowMapper<ResendedPayment> {

    private final PaymentDao paymentDao;

    @Autowired
    public ResendMapper(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public ResendedPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResendedPayment rp = new ResendedPayment();
        rp.setId(rs.getInt("id"));
        rp.setResendCount(rs.getInt("resend_count"));
        int id_payment = rs.getInt("id_payment");
        Payment payment = paymentDao.getPaymentById(id_payment);
        rp.setPayment(payment);
        return rp;
    }
}
