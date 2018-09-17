package com.axel.testpay.mapper;

import com.axel.testpay.model.Amount;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AmountMapper implements RowMapper<Amount> {
    @Override
    public Amount mapRow(ResultSet rs, int rowNum) throws SQLException {
        Amount amount = new Amount();
        amount.setId(rs.getInt("id"));
        amount.setValue(rs.getString("val"));
        amount.setCurrency(rs.getString("currency"));
        return amount;
    }
}
