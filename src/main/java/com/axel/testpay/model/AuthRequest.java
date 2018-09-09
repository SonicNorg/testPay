package com.axel.testpay.model;

import java.io.Serializable;

public class AuthRequest implements Serializable {
    private String grant_type;
    private static final long serialVersionUID = 1L;

    public String getGrantType() {
        return grant_type;
    }

    public void setGrantType(String grantType) {
        this.grant_type = grantType;
    }

    public AuthRequest(){}
}
