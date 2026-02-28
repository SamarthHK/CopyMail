package com.viveka01.copymail;
//java -cp target/classes com.viveka01.copymail.App

public class App {
    static Boolean KEY_BIND_TOGGLE = true;
    static KeyListener READER = KeyListener.getInstance();
    static ConfigManager config = ConfigManager.getInstance();
    static final String KEY_BIND = config.configReader("hotkey");

    public static void main(String[] args) {
        while (!READER.isKeyPressed("escape")) {
            if (keyToggle()) {
                Email.DefaultEmail();
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Escaped loop");
    }

    private static boolean keyToggle() {
        if (READER.isKeyBindPressed(KEY_BIND) && KEY_BIND_TOGGLE) {
            KEY_BIND_TOGGLE = false;
            System.out.println("Keybind Pressed");
            return true;
        } else if ((!(READER.isKeyBindPressed(KEY_BIND)) && !KEY_BIND_TOGGLE)) {
            System.out.println("Key Released");
            KEY_BIND_TOGGLE = true;
            return false;
        }
        return false;
    }
}
