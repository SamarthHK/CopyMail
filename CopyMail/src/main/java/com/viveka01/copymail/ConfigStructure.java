package com.viveka01.copymail;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigStructure {
    // Structure of the JSON file, and the read and write methods created for each
    // feild
    static protected class Config {
        @JsonProperty("email")
        Email EMAIL = new Email();

        @JsonProperty("hotkey")
        String HOTKEY;

        protected String ReadHotKey() {
            return HOTKEY;
        }

        protected void WriteHotKey(String HOTKEY) {
            this.HOTKEY = HOTKEY;
        }

        static protected class EMAIL {
            @JsonProperty("sender")
            String SENDER;

            protected String readSender() {
                return SENDER;
            }

            protected void writeSender(String SENDER) {
                this.SENDER = SENDER;
            }

            @JsonProperty("receiver")
            String RECEIVER;

            protected String readReceiver() {
                return RECEIVER;
            }

            protected void writeReceiver(String RECEIVER) {
                this.RECEIVER = RECEIVER;
            }

            @JsonProperty("app_password")
            String APP_PASSWORD;

            protected String readPassword() {
                return APP_PASSWORD;
            }

            protected void WritePassword(String APP_PASSWORD) {
                this.APP_PASSWORD = APP_PASSWORD;
            }
        }
    }
}
