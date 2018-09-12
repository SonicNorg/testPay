package com.axel.testpay.rest;

import com.axel.testpay.model.ErrorResponse;
import com.axel.testpay.model.Payment;
import com.axel.testpay.model.PaymentResponse;
import com.axel.testpay.model.State;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentsApi {

    @PostMapping(path = "/payments/payment", consumes = "application/json")
    public Object addPayment(
            @RequestHeader("Authorization") String authData,
            @RequestHeader("Content-type") String contentType,
            @RequestBody Payment payment) {
        //todo: набор ифоф на проверку логики
        if (authData == null) {
            ErrorResponse error = new ErrorResponse();
            error.setErrorCode("400");
            error.setErrorDescription("Bad Request");
            return error;
        }
        PaymentResponse response = new PaymentResponse();
        response.setId("tralalal");
        response.setState(State.createrd);
        return response;
    }
}
