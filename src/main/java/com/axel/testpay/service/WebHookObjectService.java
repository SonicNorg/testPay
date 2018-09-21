package com.axel.testpay.service;

import com.axel.testpay.model.Payment;
import com.axel.testpay.model.WebHookObject;

import java.security.NoSuchAlgorithmException;

public interface WebHookObjectService {
    WebHookObject createObject(Payment payment) throws NoSuchAlgorithmException;
}
