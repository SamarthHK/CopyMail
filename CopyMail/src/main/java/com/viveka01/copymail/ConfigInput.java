package com.viveka01.copymail;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigInput {
    static Scanner INPUT = new Scanner(System.in);
    static final String EMAIL_PATTERN = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$";
    static Pattern EMAIL_FORMAT = Pattern.compile(EMAIL_PATTERN);

    // Taking email input and checking it with regex
    private static String emailInput() {
        System.out.println("Input Email");
        String EMAIL = INPUT.nextLine();
        while (!emailCheck(EMAIL)) {
            System.out.println("Input a proper Email");
            EMAIL = INPUT.nextLine();
        }
        return EMAIL;
    }

    private static boolean emailCheck(String EMAIL_ADDRESS) {
        Matcher EMAIL_MATCH = EMAIL_FORMAT.matcher(EMAIL_ADDRESS);
        return EMAIL_MATCH.matches();
    }

    // Taking App password input
    /*
     * Note to self, add password verification system
     */
    private static String passwordInput() {
        System.out.println("Input your app password");
        return INPUT.nextLine();
    }

    // Taking hotkey as input
    private static String hotKeyInput() {
        return HotKeyInput.hotKeyInput();
    }

    // Taking bot email input
    private static String senderEmailInput() {
        System.out.println("Taking input of your bot (sending) email");
        return emailInput();
    }

    // taking receiving email input
    private static String receiverEmailInput() {
        System.out.println("Taking input of your recipeint email");
        return emailInput();
    }
}
