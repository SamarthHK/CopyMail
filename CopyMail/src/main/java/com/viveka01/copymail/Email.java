package com.viveka01.copymail;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class Email {

    private Properties PropCreate() {
        // ============================
        // 1. Configure SMTP Properties
        // ============================

        Properties PROP = new Properties();

        // SMTP server address (Gmail in this case)
        PROP.put("mail.smtp.host", "smtp.gmail.com");

        // Port 587 = SMTP with STARTTLS
        PROP.put("mail.smtp.port", "587");

        // Enable authentication
        PROP.put("mail.smtp.auth", "true");

        // Enable TLS encryption
        PROP.put("mail.smtp.starttls.enable", "true");

        return PROP;
    }

    private Session SessionCreate(Properties PROP, String SENDER, String APP_PASSWORD) {
        // ============================
        // 2. Create a Session
        // ============================

        // This stores configuration + login credentials
        Session session = Session.getInstance(PROP,
                new Authenticator() {

                    // This runs when the SMTP server asks for login
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                SENDER, // your email
                                APP_PASSWORD // Gmail app password
                        );
                    }
                });
        return session;
    }

    public static void main(String[] args) {

        try {

            // ============================
            // 3. Create the Email Message
            // ============================

            Message message = new MimeMessage(session);

            // Set sender
            message.setFrom(new InternetAddress("your_email@gmail.com"));

            // Set recipient
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("recipient@gmail.com"));

            // Set subject line
            message.setSubject("Hello from Jakarta Mail!");

            // Set email body text
            message.setText("This email was sent using Jakarta Mail in Java.");

            // ============================
            // 4. Send the Email
            // ============================

            // This is where the actual connection happens
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}