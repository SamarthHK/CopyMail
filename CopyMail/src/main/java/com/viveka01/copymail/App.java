package com.viveka01.copymail;
//java -cp target/classes com.viveka01.copymail.App

public class App {
    static final String KEY_BIND = "A";
    static Boolean KEY_BIND_TOGGLE = true;
    static KeyListener READER = KeyListener.getInstance();

    public static void main(String[] args) {
        ConfigManager.getInstance();
        // while (!READER.isKeyPressed("Escape")) {
        // KeyToggle();
        // try {
        // Thread.sleep(10);
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // }
        // System.out.println("Function Escaped");
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
