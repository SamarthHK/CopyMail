package com.viveka01.copymail;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigInput {
    static Scanner INPUT = new Scanner(System.in);
    static final String EMAIL_PATTERN = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$";
    static Pattern EMAIL_FORMAT = Pattern.compile(EMAIL_PATTERN);

    // Taking email input and checking it with regex
    public static String emailInput() {
        System.out.println("Input Email");
        String EMAIL = INPUT.nextLine();
        Matcher EMAIL_MATCH = EMAIL_FORMAT.matcher(EMAIL);
        while (!EMAIL_MATCH.find()) {
            System.out.println("Input a proper Email");
            EMAIL = INPUT.nextLine();
            EMAIL_MATCH = EMAIL_FORMAT.matcher(EMAIL);
        }
        return EMAIL;
    }

}
