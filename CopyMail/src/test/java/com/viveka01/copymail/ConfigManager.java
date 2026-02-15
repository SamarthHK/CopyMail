package com.viveka01.copymail;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class ConfigManager {
    private static Scanner INPUT = new Scanner(System.in);

    protected static boolean fileCheck() {
    File FILE = new File("config.json");
    return FILE.exists();
    }
    protected static void createConfig() {
    
    }
    private boolean emailTest(String EMAIL) {
        final Pattern EMAIL_TEMP = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        return EMAIL_TEMP.matcher(EMAIL).matches();
    }
    private void emailInput() {
        System.out.println("Input an email:");
        String EMAIL = INPUT.nextLine();
        while (!emailTest(EMAIL)) {
            System.out.println("Input a valid email:");
            EMAIL = INPUT.nextLine();
        }
        System.out.println(EMAIL);
    }
    private String hotKeyInput(){
        
    }
}