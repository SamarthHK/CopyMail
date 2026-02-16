package com.viveka01.copymail;

import java.io.*;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.exc.StreamReadException;

public class ConfigManager {
    private static final File CONFIG = new File("config.json");
    static ObjectMapper OBJECTMAPPER = new ObjectMapper();

    // Creates a Singleton of the ConfigManager class
    private static class Holder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    // Once called, Creates the singleton and calls Constructor
    public static ConfigManager getInstance() {
        return Holder.INSTANCE;
    }

    // Constructor that Initializes the whole Config
    private ConfigManager() {
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

    // Identifying if the config exists, if it doesnt, creates a blank one
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

    // File deletion
    static private void configDelete() {
        if (CONFIG.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }

    // Structure for Jackson, Structure of config file
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
