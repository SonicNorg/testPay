package com.axel.testpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestpayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestpayApplication.class, args);
    }
}
