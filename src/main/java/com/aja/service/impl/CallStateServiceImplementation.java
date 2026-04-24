package com.aja.service.impl;

import com.aja.service.CallStateService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CallStateServiceImplementation implements CallStateService {

    // store user -> status
    private final ConcurrentHashMap<String, Boolean> userStatus = new ConcurrentHashMap<>();

    @Override
    public void setUserBusy(String user) {
        userStatus.put(user, true);
    }

    @Override
    public void setUserFree(String user) {
        userStatus.put(user, false);
    }

    @Override
    public boolean isUserBusy(String user) {
        return userStatus.getOrDefault(user, false);
    }
}