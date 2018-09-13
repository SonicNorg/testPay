package com.axel.testpay.rest;

import com.axel.testpay.model.ErrorResponse;
import com.axel.testpay.model.Payment;
import com.axel.testpay.model.PaymentResponse;
import com.axel.testpay.model.State;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class PaymentsApi {

    @PostMapping(path = "/payments/payment", consumes = "application/json")
    public Object addPayment(
            @RequestHeader("Authorization") String authData,
            @RequestHeader("Content-type") String contentType,
            @RequestBody Payment payment) {
        if (authData == null) {
            ErrorResponse error = new ErrorResponse();
            error.setErrorCode("400");
            error.setErrorDescription("Bad Request");
            return error;
        }
        PaymentResponse response = new PaymentResponse();
        response.setId("random id");
        response.setCreateDate(new Date());
        response.setState(State.createrd);
        return response;
    }
}
