package com.axel.testpay.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Payment extends AbstractEntity {

    private Intent intent;

    @JsonSetter("notification_url")
    private String notificationUrl;

    private Payer payer;

    private Transaction transaction;

    private State state;

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Payment() {
    }
}
