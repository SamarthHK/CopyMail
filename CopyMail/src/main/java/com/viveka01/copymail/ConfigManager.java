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
        configInit();
        try {
            Config CONFIG_OBJ = OBJECTMAPPER.readValue(CONFIG, Config.class);
        } catch (StreamReadException e) {
            e.printStackTrace();
            System.out.println("Invalid config, deleting and re-initializing");
            configDelete();
            configInit();
        } catch (DatabindException e) {
            e.printStackTrace();
            System.out.println("Invalid config structure, deleting and re-initializing");
            configDelete();
            configInit();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("GG's");
        }
    }

    static private void configInit() {
        if (!CONFIG.exists()) {
            try {
                CONFIG.createNewFile();
                System.out.println("Config created sucsessfully");
            } catch (IOException e) {
                System.out.println("Config creation failed :(...");
            }
        } else {
            System.out.println("Config found");
        }
    }

    static private void configDelete() {
        if (CONFIG.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }

    static void valueAssignment() throws StreamReadException, DatabindException, IOException {

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
