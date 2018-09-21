package com.axel.testpay.service.impl;

import com.axel.testpay.model.Payment;
import com.axel.testpay.model.State;
import com.axel.testpay.model.WebHookObject;
import com.axel.testpay.service.WebHookObjectService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


@Service
public class WebHookObjectServiceImpl implements WebHookObjectService {
    @Override
    public WebHookObject createObject(Payment payment) throws NoSuchAlgorithmException {
        WebHookObject webHookObject = new WebHookObject();
        webHookObject.setCurrency(payment.getTransaction().getAmount().getCurrency());
        webHookObject.setAmount(payment.getTransaction().getAmount());
        webHookObject.setExternalId(payment.getTransaction().getExternalId());
        webHookObject.setState(State.approved);
        Random random = new Random();
        webHookObject.setId(random.nextInt(100));

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        String secret = "secret words";
        char[] chars = secret.toCharArray();
        byte[] asciEqv = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            asciEqv[i] = (byte) chars[i];
        }
        byte[] secretDigest = digest.digest(asciEqv);
        String forSha = payment.getTransaction().getAmount().getCurrency() +
                payment.getTransaction().getAmount() +
                secretDigest.toString().toUpperCase() +
                webHookObject.getId() +
                payment.getTransaction().getExternalId() +
                webHookObject.getState();
        byte[] sha = digest.digest(forSha.getBytes(StandardCharsets.UTF_8));

        webHookObject.setSha2sig(sha.toString());
        return webHookObject;
    }
}
