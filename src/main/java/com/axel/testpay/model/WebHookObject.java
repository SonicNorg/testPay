package com.axel.testpay.model;

public class WebHookObject extends AbstractEntity {
    private String sha2sig;
    private String currency;
    private Amount amount;
    private String externalId;
    private State state;

    public String getSha2sig() {
        return sha2sig;
    }

    public void setSha2sig(String sha2sig) {
        this.sha2sig = sha2sig;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public WebHookObject() {
    }
}
