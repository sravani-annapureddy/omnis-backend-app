package com.aja.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aja.service.SmsService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import jakarta.annotation.PostConstruct;

@Service 
public class SmsServiceImplementation implements SmsService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromNumber;  // ✅ fixed name

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void sendSms(String to, String message) {
        Message.creator(
                new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber(fromNumber),
                message
        ).create();
    }
}