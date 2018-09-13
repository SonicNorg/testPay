package com.axel.testpay.rest;

        import com.axel.testpay.model.AuthRequest;
        import com.axel.testpay.model.AuthResponse;
        import com.axel.testpay.model.ErrorResponse;
        import org.springframework.web.bind.annotation.*;

@RestController
public class Authorization {

    @PostMapping(path = "/oauth2/token", consumes = "application/json")
    public Object getToken(
            @RequestHeader("Accept") String contentType,
            @RequestHeader("Accept-Language") String lang,
            @RequestBody AuthRequest authRequest) {
        if (authRequest.getGrantType() != null && authRequest.getGrantType().equals("client_credentials")) {
            AuthResponse response = new AuthResponse();
            response.setAccessToken("test token");
            response.setExpiresIn(50000L);
            response.setScope("https://api.testpay.com/payments/.*");
            response.setTokenType("Bearer");
            return response;
        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorCode("401");
            errorResponse.setErrorDescription("AUTHENTIFICATION_FAILURE");
            return errorResponse;
        }
    }
}
