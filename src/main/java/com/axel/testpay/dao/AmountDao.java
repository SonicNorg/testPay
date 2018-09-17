package com.axel.testpay.dao;

import com.axel.testpay.model.Amount;

public interface AmountDao {
    void createAmount(Amount amount);
    void updateAmount(Amount amount);
    void deleteAmount(Amount amount);
    Amount getAmountById(int id);

}
