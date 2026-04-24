package com.aja.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aja.service.impl.CallServiceImplementation;
import com.aja.service.impl.CallStateServiceImplementation;

@RestController
@RequestMapping("/calls")
public class CallController {

    private final CallServiceImplementation callServiceImpl;
    private final CallStateServiceImplementation callStateService;

    public CallController(CallServiceImplementation callServiceImpl,
                          CallStateServiceImplementation callStateService) {
        this.callServiceImpl = callServiceImpl;
        this.callStateService = callStateService;
    }

    @GetMapping("/real-call")
    public String makeCall(@RequestParam String fromNumber,
                           @RequestParam String toNumber) {

        // 🔴 check busy
        if (callStateService.isUserBusy(toNumber)) {
            return "User is BUSY ❌";
        }

        // ✅ mark busy
        callStateService.setUserBusy(fromNumber);
        callStateService.setUserBusy(toNumber);

        // 📞 call using Twilio
        return callServiceImpl.makeCall(fromNumber, toNumber);
    }
    @PostMapping("/end")
    public String endCall(@RequestParam String fromUser,
                          @RequestParam String toUser) {

        // ✅ free both users
        callStateService.setUserFree(fromUser);
        callStateService.setUserFree(toUser);

        return "Call Ended";
    }

    @GetMapping("/status")
    public boolean isBusy(@RequestParam String user) {
        return callStateService.isUserBusy(user);
    }
}