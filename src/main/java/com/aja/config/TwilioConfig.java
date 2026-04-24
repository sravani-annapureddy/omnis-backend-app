package com.aja.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioConfig {

    private static final String ACCOUNT_SID = "ACxxxxxxxxxxxxxxxxxxxx";
    private static final String AUTH_TOKEN = "your_auth_token";

    @PostConstruct
    public void init() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        System.out.println("Twilio Initialized Successfully");
    }
}