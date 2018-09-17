package com.axel.testpay.dao.impl;

import com.axel.testpay.dao.AmountDao;
import com.axel.testpay.dao.TransactionDao;
import com.axel.testpay.mapper.TransactionalMapper;
import com.axel.testpay.model.Transaction;
import com.axel.testpay.util.QuerryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DefaultTransactionDao implements TransactionDao {

    private final JdbcTemplate jdbcTemplate;

    private final AmountDao amountDao;

    @Autowired
    public DefaultTransactionDao(JdbcTemplate jdbcTemplate, AmountDao amountDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.amountDao = amountDao;
    }


    @Override
    public void createTransaction(Transaction transaction) throws IllegalStateException {
        if (transaction.getAmount() == null) {
            throw new IllegalStateException("Transaction with id:"+transaction.getId()+" haven't amount");
        }
        String sql = "INSERT INTO transacts(id, external_id, description, amount_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, transaction.getId(), transaction.getExternalId(), transaction.getDescription(), transaction.getAmount().getId());
    }

    @Override
    public void updateTransaction(Transaction transaction) throws IllegalStateException  {
        if (transaction.getAmount() == null) {
            throw new IllegalStateException("Transaction with id:"+transaction.getId()+" haven't amount");
        }
        String sql = "UPDATE transacts SET external_id  = ?, description = ?, amount_id = ?";
        jdbcTemplate.update(sql, transaction.getExternalId(), transaction.getDescription(), transaction.getAmount().getId());
    }

    @Transactional
    @Override
        public void deleteTransaction(Transaction transaction) throws IllegalStateException {
        if (transaction.getAmount() == null) {
            throw new IllegalStateException("Transaction with id:"+transaction.getId()+" haven't amount");
        }
        String sql = QuerryUtils.getDeleteQueryForEntity("transacts", transaction.getId());
        amountDao.deleteAmount(transaction.getAmount());
        jdbcTemplate.update(sql);
    }

    @Override
    public Transaction getTransactionById(int id) {
        String sql = QuerryUtils.getSelectQueryForEntity("transacts", id);
        RowMapper<Transaction> rowMapper = new TransactionalMapper(amountDao);
        return this.jdbcTemplate.queryForObject(sql, rowMapper);
    }
}
