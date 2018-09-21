package com.axel.testpay.rest;

import com.axel.testpay.model.*;
import com.axel.testpay.service.PaymentService;
import com.axel.testpay.service.PaymentVerifyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PaymentsApi {

    private final PaymentService paymentService;

    @Autowired
    public PaymentsApi(PaymentService paymentService, PaymentVerifyerService paymentVerifyerService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(path = "/payments/payment", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<AbstractResponse> addPayment(@RequestBody Payment payment) {
        if (paymentService.acceptPayment(payment)) {
            PaymentResponse response = new PaymentResponse();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            response.setCreateDate(sdf.format(new Date()));
            response.setId(String.valueOf(payment.getId()));
            response.setState(State.created);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorCode("INVALID_REQUEST");
            errorResponse.setErrorDescription("Request is not well-formatted, syntactically incorrect or violates schema");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
