package com.aja.service;

public interface CallStateService {

    void setUserBusy(String user);

    void setUserFree(String user);

    boolean isUserBusy(String user);
}