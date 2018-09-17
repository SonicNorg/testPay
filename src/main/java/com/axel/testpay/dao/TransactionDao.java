package com.axel.testpay.dao;

import com.axel.testpay.model.Transaction;

public interface TransactionDao {
    void createTransaction(Transaction transaction);
    void updateTransaction(Transaction transaction);
    void deleteTransaction(Transaction transaction);
    Transaction getTransactionById(int id);
}
