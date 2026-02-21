package com.viveka01.copymail;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class Email {
    // Creating the properties for the configuration of SMTP
    private final static Properties PROP = new Properties();
    private static final Boolean AUTHENTICATION = true;
    private static final Boolean TLS = true;
    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 587;

    // Loading constant values into properties
    static private void LoadProperty() {
        PROP.put("mail.smtp.auth", AUTHENTICATION);
        PROP.put("mail.smtp.starttls.enable", TLS);
        PROP.put("mail.smtp.host", HOST);
        PROP.put("mail.smtp.port", PORT);
    }

    static private void CreateSession(){
        Session SESSION = Session.getInstance(PROP,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return PasswordAuthentication(,);
            }
        })
    }

}