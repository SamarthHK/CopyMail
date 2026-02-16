package com.viveka01.copymail;

import java.io.*;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.exc.StreamReadException;

public class ConfigManager {
    private static final File CONFIG = new File("config.json");
    static ObjectMapper OBJECTMAPPER = new ObjectMapper();
    static {
        if (!CONFIG.exists()) {
            try {
                CONFIG.createNewFile();
                System.out.println("Config created sucsessfully");
            } catch (IOException e) {
                System.out.println("Config creation failed :(...");
            }
        }
    }

    static void valueAssignment() throws StreamReadException, DatabindException, IOException {
        Config CONFIG = OBJECTMAPPER.readValue(CONFIG, Config.class);
    }

    static private class Config {
        @JsonProperty("email")
        Email EMAIL = new Email();
        @JsonProperty("hotkey")
        String HOTKEY;

        static private class Email {
            @JsonProperty("sender")
            String SENDER;
            @JsonProperty("receiver")
            String RECEIVER;
            @JsonProperty("app_password")
            String APP_PASSWORD;
        }
    }

}
