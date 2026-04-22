package com.aja.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.aja.config.EmailConfig;
//import com.aja.config.EmailConfig;
import com.aja.service.EmailService;

@Service
public class EmailServiceImplementation implements EmailService {
    
    private final JavaMailSender javaMailSender;
    
    @Value("${spring.mail.username}")
    private String fromEmail;
 
    
    public EmailServiceImplementation(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);
        mail.setFrom(fromEmail);
        
//       mail.setFrom(EmailConfig.USERNAME); 

        javaMailSender.send(mail);
    }
}