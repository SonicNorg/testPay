package com.axel.testpay.dao.impl;

import com.axel.testpay.dao.PayerDao;
import com.axel.testpay.mapper.PayerMapper;
import com.axel.testpay.model.Payer;
import com.axel.testpay.util.QuerryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultPayerDao implements PayerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createPayer(Payer payer) {
        String sql = "INSERT INTO payer (id, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, payer.getId(), payer.getEmail());
    }

    @Override
    public void updatePayer(Payer payer) {
        String sql = "UPDATE payer SET email = ? WHERE id=?";
        jdbcTemplate.update(sql, payer.getEmail(), payer.getId());
    }

    @Override
    public void deletePayer(Payer payer) {
        String sql = QuerryUtils.getDeleteQueryForEntity("payer", payer.getId());
        jdbcTemplate.update(sql);
    }

    @Override
    public Payer getPayerById(int id) {
        String sql = QuerryUtils.getSelectQueryForEntity("payer", id);
        RowMapper<Payer> mapper = new PayerMapper();
        return this.jdbcTemplate.queryForObject(sql, mapper);
    }
}
