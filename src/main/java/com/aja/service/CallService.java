package com.aja.service;

public interface CallService {
	
	String makeCall(String fromUser, String toUser);
	String endCall(String userPhone);
	boolean isUserBusy(String userPhone);

}
