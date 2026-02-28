package com.viveka01.copymail;
//java -cp target/classes com.viveka01.copymail.App

public class App {
    static final String KEY_BIND = "A";
    static Boolean KEY_BIND_TOGGLE = true;
    static KeyListener READER = KeyListener.getInstance();
    static {
        ConfigManager.getInstance();
    }

    public static void main(String[] args) {
    }

    private static void KeyToggle() {
        if (READER.isKeyPressed(KEY_BIND) && KEY_BIND_TOGGLE) {
            KEY_BIND_TOGGLE = false;
            System.out.println("Key Pressed");
        } else if ((!(READER.isKeyPressed(KEY_BIND)) && !KEY_BIND_TOGGLE)) {
            System.out.println("Key Released");
            KEY_BIND_TOGGLE = true;
        }
    }
}
