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
        int retry = 0;
        int[] waitTimes = { 1, 2, 5, 10 }; // in seconds
        while (true) {
            try {
                configInit();
                CONFIG_OBJ = OBJECTMAPPER.readValue(CONFIG, ConfigStructure.Config.class);
                if (isConfigEmpty()) {
                    System.out.println("Re-writing");
                    configReWrite();
                }
                break; // success, exit loop
            } catch (StreamReadException | DatabindException e) {
                e.printStackTrace();
                System.out.println("Invalid config, deleting and re-initializing");
                configDelete();

                if (retry >= waitTimes.length) {
                    System.out.println("Max retries reached, aborting.");
                    break; // stop trying
                }

                try {
                    int waitTime = waitTimes[retry];
                    System.out.println("Waiting " + waitTime + " seconds before retry...");
                    Thread.sleep(waitTime * 1000L);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }

                retry++;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("GG's");
                break;
            }
        }
    }

    // Identifying if the config exists, if it doesnt, creates a blank one
    static private void configInit() {
        if (!CONFIG.exists()) {
            try {
                CONFIG.createNewFile();
                System.out.println("Config created sucsessfully");
                createDefaultConfigFile();
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

    private boolean isConfigEmpty() {
        if (configReader("sender").isEmpty()) {
            return true;
        }
        System.out.println("Sender not empty");
        if (configReader("receiver").isEmpty()) {
            return true;
        }
        System.out.println("receiver not empty");
        if (configReader("password").isEmpty()) {
            return true;
        }
        System.out.println("password not empty");
        if (configReader("hotkey").isEmpty()) {
            return true;
        }
        System.out.println("hotkey not empty");
        return false;
    }

    private void configReWrite() {
        configDelete();
        DefaultInputReturnStructure configInputs = ConfigInput.defaultInput();
        configFill(configInputs.getSenderEmail(), configInputs.getReceiverEmail(), configInputs.getReceiverEmail(),
                configInputs.getHotKey());
        configWrite();
    }

    static private void createDefaultConfigFile() {
        ConfigStructure.Config defaultConfig = new ConfigStructure.Config();
        // fill default values so Jackson can write it
        defaultConfig.Email.writeSender("");
        defaultConfig.Email.writeReceiver("");
        defaultConfig.Email.writePassword("");
        defaultConfig.writeHotKey("");

        try {
            OBJECTMAPPER.writeValue(CONFIG, defaultConfig); // write proper JSON to file
            System.out.println("Created a new empty config");
        } catch (IOException e) {
            e.printStackTrace();
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
            case "hotkey":
                return CONFIG_OBJ.readHotKey();
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
