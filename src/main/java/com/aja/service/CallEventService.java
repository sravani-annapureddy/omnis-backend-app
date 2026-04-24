package com.aja.service;

public interface CallEventService {

    void sendIncomingCall(String toUser, String fromUser);

    void sendCallStatus(String user, String status);
}