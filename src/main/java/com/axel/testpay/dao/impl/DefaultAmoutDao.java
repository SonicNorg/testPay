package com.axel.testpay.dao.impl;

import com.axel.testpay.dao.AmountDao;
import com.axel.testpay.mapper.AmountMapper;
import com.axel.testpay.model.Amount;
import com.axel.testpay.util.QuerryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultAmoutDao implements AmountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createAmount(Amount amount) {
        String sql = "INSERT INTO amount (id, val, currency) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, amount.getId(), amount.getValue(), amount.getCurrency());
    }

    @Override
    public void updateAmount(Amount amount) {
        String sql = "UPDATE amount SET val=?, currency=? WHERE id=?";
        jdbcTemplate.update(sql, amount.getValue(), amount.getCurrency());
    }

    @Override
    public void deleteAmount(Amount amount) {
        String sql = QuerryUtils.getDeleteQueryForEntity("amount", amount.getId());
        jdbcTemplate.update(sql);
    }

    @Override
    public Amount getAmountById(int id) {
        String sql = QuerryUtils.getSelectQueryForEntity("amount", id);
        RowMapper<Amount> rowMapper = new AmountMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper);
    }
}
