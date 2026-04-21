package com.aja.config;

import io.github.cdimascio.dotenv.Dotenv;

public class TwilioConfig {

    private static final Dotenv dotenv = Dotenv.load();

    public static final String ACCOUNT_SID = dotenv.get("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = dotenv.get("TWILIO_AUTH_TOKEN");
    public static final String PHONE_NUMBER = dotenv.get("TWILIO_PHONE_NUMBER");
}