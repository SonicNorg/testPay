package com.axel.testpay.dao;

import com.axel.testpay.model.Payer;

public interface PayerDao {
    void createPayer(Payer payer);
    void updatePayer(Payer payer);
    void deletePayer(Payer payer);
    Payer getPayerById(int id);
}
