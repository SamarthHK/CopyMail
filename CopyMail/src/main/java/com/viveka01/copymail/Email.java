package com.viveka01.copymail;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class Email {
    // Creating the properties for the configuration of SMTP
    private final static Properties PROP = new Properties();
    private static final String AUTHENTICATION = "true";
    private static final String TLS = "true";
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";

    private static Session SESSION;

    // Loading constant values into properties
    static private void LoadProperty() {
        PROP.put("mail.smtp.auth", AUTHENTICATION);
        PROP.put("mail.smtp.starttls.enable", TLS);
        PROP.put("mail.smtp.host", HOST);
        PROP.put("mail.smtp.port", PORT);
        System.out.println("Properties defigned, LoadProperty ran");
    }

    // Creates a session
    static private Session CreateSession() {
        LoadProperty();
        Session SESSION = Session.getInstance(PROP, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ConfigManager.getInstance().configReader("sender"),
                        ConfigManager.getInstance().configReader("password"));
            }
        });
        SESSION.setDebug(true);
        System.out.println("Session created sucsessfully, CreateSession ran");
        return SESSION;

    }

    // Lazy initialization
    /*
     * Note to self
     * Add a functionality where it checks if the config has changed, if the config
     * has changed then re-initialize the session
     */
    static private Session getSession() {
        if (SESSION == null) {
            SESSION = CreateSession();
        }
        return SESSION;
    }

    // Creates a draft of the email with sender and receiver added
    static private MimeMessage EmailDraft() {
        MimeMessage DRAFT = new MimeMessage(getSession());
        try {
            DRAFT.setFrom(new InternetAddress(ConfigManager.getInstance().configReader("sender")));
            DRAFT.setRecipients(MimeMessage.RecipientType.TO,
                    InternetAddress.parse(ConfigManager.getInstance().configReader("receiver")));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Email Drafted sucsessfully, EmailDraft ran");
        return DRAFT;
    }

    // Default enail format used by copy mail
    public static void DefaultEmail() {
        MimeMessage EMAIL = EmailDraft();
        try {
            EMAIL.setSubject("CopyMail");
            EMAIL.setText(ClipBoardReader.readClipBoard());
            Transport.send(EMAIL);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Email Sent sucsessfully, DefaultEmail ran");
    }
    /*
     * Note to self
     * add a email verification part to this
     */
}
