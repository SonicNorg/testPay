package com.axel.testpay.model;

import java.util.Date;

public class PaymentResponse extends AbstractResponse {
    private String id;
    private String createDate;
    private State state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public PaymentResponse() {
    }
}
