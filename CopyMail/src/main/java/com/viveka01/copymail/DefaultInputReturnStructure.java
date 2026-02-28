package com.viveka01.copymail;

public class DefaultInputReturnStructure {

    private String senderEmail;
    private String receiverEmail;
    private String appPassword;
    private String hotKey;

    public DefaultInputReturnStructure(String senderEmail,
            String appPassword,
            String receiverEmail,
            String hotKey) {
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.appPassword = appPassword;
        this.hotKey = hotKey;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public String getAppPassword() {
        return appPassword;
    }

    public String getHotKey() {
        return hotKey;
    }
}