package com.axel.testpay.model;

import java.util.Date;

public class PaymentResponse {
    private String id;
    private Date createDate;
    private State state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
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
