package com.axel.testpay.mapper;

import com.axel.testpay.dao.AmountDao;
import com.axel.testpay.model.Amount;
import com.axel.testpay.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TransactionalMapper implements RowMapper<Transaction> {

    private final AmountDao amountDao;

    @Autowired
    public TransactionalMapper(AmountDao amountDao) {
        this.amountDao = amountDao;
    }

    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getInt("id"));
        transaction.setExternalId(rs.getString("external_id"));
        transaction.setDescription(rs.getString("description"));
        int amountId = rs.getInt("amount_id");
        Amount amount = amountDao.getAmountById(amountId);
        transaction.setAmount(amount);
        return transaction;
    }
}
