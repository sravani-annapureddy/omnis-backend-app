package com.aja.service.impl;

import com.aja.dto.CallEvent;
import com.aja.service.CallEventService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class CallEventServiceImplementation implements CallEventService {

    private final SimpMessagingTemplate messagingTemplate;

    public CallEventServiceImplementation(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendIncomingCall(String toUser, String fromUser) {
        messagingTemplate.convertAndSend(
                "/topic/call/" + toUser,
                new CallEvent("INCOMING_CALL", "Call from " + fromUser)
        );
    }

    @Override
    public void sendCallStatus(String user, String status) {
        messagingTemplate.convertAndSend(
                "/topic/call/" + user,
                new CallEvent("STATUS", status)
        );
    }}