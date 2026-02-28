package com.viveka01.copymail;

import java.io.*;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.exc.StreamReadException;

public class ConfigManager {
    private static final File CONFIG = new File("config.json");
    static ObjectMapper OBJECTMAPPER = new ObjectMapper();
    private static ConfigStructure.Config CONFIG_OBJ;

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
            CONFIG_OBJ = OBJECTMAPPER.readValue(CONFIG, ConfigStructure.Config.class);
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

    // File reading
    public String configReader(String ITEM) {
        getInstance();
        switch (ITEM) {
            case "sender":
                return CONFIG_OBJ.Email.readSender();
            case "receiver":
                return CONFIG_OBJ.Email.readReceiver();
            case "password":
                return CONFIG_OBJ.Email.readPassword();
            default:
                return null;
        }

    }

    public void configFill(String SENDER, String RECIEVER, String PASSWORD, String HOTKEY) {
        CONFIG_OBJ.writeHotKey(HOTKEY);
        CONFIG_OBJ.Email.writePassword(PASSWORD);
        CONFIG_OBJ.Email.writeReceiver(RECIEVER);
        CONFIG_OBJ.Email.writeSender(SENDER);
    }

    public void configWrite() {
        try {
            OBJECTMAPPER.writeValue(CONFIG, CONFIG_OBJ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
