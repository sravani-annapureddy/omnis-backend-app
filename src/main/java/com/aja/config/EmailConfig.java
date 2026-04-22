package com.aja.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EmailConfig {

    private static final Dotenv dotenv = Dotenv.load();

    public static final String USERNAME = dotenv.get("MAIL_USERNAME");
    public static final String PASSWORD = dotenv.get("MAIL_PASSWORD");
    public static final String HOST = dotenv.get("MAIL_HOST");

    public static final int PORT;

    static {
        String portStr = dotenv.get("MAIL_PORT");

        if (portStr == null) {
            throw new RuntimeException("MAIL_PORT is missing in .env file");
        }

        PORT = Integer.parseInt(portStr);
    }
}