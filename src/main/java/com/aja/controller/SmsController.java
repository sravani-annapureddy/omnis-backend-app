package com.aja.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aja.service.impl.SmsServiceImplementation;

@RestController
@RequestMapping("/sms")
public class SmsController {

   
    private SmsServiceImplementation smsServiceImpl;
    
    public SmsController(SmsServiceImplementation smsServiceImpl) {
		super();
		this.smsServiceImpl = smsServiceImpl;
	}



    @GetMapping("/send")
    public String sendSms(@RequestParam String to,
                          @RequestParam String message) {
        smsServiceImpl.sendSms(to, message);
        return "SMS sent successfully!";
    }
}

