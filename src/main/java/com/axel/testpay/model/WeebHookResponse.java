package com.axel.testpay.model;

public class WeebHookResponse extends AbstractResponse {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
