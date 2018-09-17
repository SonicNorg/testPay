package com.axel.testpay.mapper;

import com.axel.testpay.model.Payer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PayerMapper implements RowMapper<Payer> {

    @Override
    public Payer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payer payer = new Payer();
        payer.setId(rs.getInt("id"));
        payer.setEmail(rs.getString("email"));
        return payer;
    }
}
