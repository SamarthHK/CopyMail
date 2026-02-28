package com.viveka01.copymail;
//java -cp target/classes com.viveka01.copymail.App

public class App {
    static final String KEY_BIND = "a";
    static Boolean KEY_BIND_TOGGLE = true;
    static KeyListener READER = KeyListener.getInstance();
    static {
        ConfigManager.getInstance();
    }

    public static void main(String[] args) {
        while (!READER.isKeyPressed("escape")) {
            KeyToggle();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Escaped loop");
    }

    private static void KeyToggle() {
        if (READER.isKeyBindPressed(KEY_BIND) && KEY_BIND_TOGGLE) {
            KEY_BIND_TOGGLE = false;
            System.out.println("Key Pressed");
        } else if ((!(READER.isKeyBindPressed(KEY_BIND)) && !KEY_BIND_TOGGLE)) {
            System.out.println("Key Released");
            KEY_BIND_TOGGLE = true;
        }
    }
}
