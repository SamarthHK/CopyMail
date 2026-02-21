package com.viveka01.copymail;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigStructure {
    static protected class Config {
        @JsonProperty("email")
        Email EMAIL = new Email();
        @JsonProperty("hotkey")
        String HOTKEY;

        static protected class Email {
            @JsonProperty("sender")
            String SENDER;
            @JsonProperty("receiver")
            String RECEIVER;
            @JsonProperty("app_password")
            String APP_PASSWORD;
        }
    }
}
