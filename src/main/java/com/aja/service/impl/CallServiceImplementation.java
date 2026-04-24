package com.aja.service.impl;

import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aja.service.CallService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

@Service
public class CallServiceImplementation implements CallService {

    private final ConcurrentHashMap<String, Boolean> callStatus = new ConcurrentHashMap<>();

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioNumber;

    // ✅ FIXED: Use injected values
//    @PostConstruct
//    public void init() {
//        Twilio.init(accountSid, authToken);
//    }
    
    @PostConstruct
    public void init() {
        System.out.println("SID: " + accountSid);
        System.out.println("TOKEN: " + authToken);
        System.out.println("TWILIO NUM: " + twilioNumber);

        Twilio.init(accountSid, authToken);
    }

    @Override
    public String makeCall(String fromNumber, String toNumber) {

    	Call call = Call.creator(
    	        new PhoneNumber(toNumber),
    	        new PhoneNumber(twilioNumber), // ✅ use Twilio number
    	        URI.create("http://demo.twilio.com/docs/voice.xml")
    	).create();

        return call.getSid();
    }

    @Override
    public String endCall(String userPhone) {
        callStatus.put(userPhone, false);
        return "CALL ENDED";
    }

    @Override
    public boolean isUserBusy(String userPhone) {
        return callStatus.getOrDefault(userPhone, false);
    }
}