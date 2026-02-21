package com.viveka01.copymail;

import java.util.HashSet;

public class HotKeyInput {
    static KeyListener READER = KeyListener.getInstance();
    static private String KEY_BIND;
    static private Timer TIME = new Timer();

    public static String hotKeyInput() {
        waitForKeyRelease();
        System.out.println("Release all keys");
        System.out.println("Hold they keybind you wish to use");
        return recordKeyBind(3);

    }

    static private void waitForKeyRelease() {
        while (!READER.isAnyKeyPressed().isEmpty()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getCurrentKeyBind() {
        HashSet<String> pressed = READER.isAnyKeyPressed();
        return String.join("+", pressed);
    }

    private static String recordKeyBind(int DURATION) {
        String LAST_KEY_BIND = null;
        TIME.start();
        while (!READER.isKeyPressed("Escape")) {
            KEY_BIND = getCurrentKeyBind();
            if (!KEY_BIND.equals(LAST_KEY_BIND) || KEY_BIND.equals("")) {
                LAST_KEY_BIND = KEY_BIND;
                TIME.reset();
            }
            if (KEY_BIND.equals(LAST_KEY_BIND) && TIME.getElapsedMillis() == 0 && !KEY_BIND.equals("")) {
                System.out.println("Hold " + KEY_BIND + " For " + DURATION + " seconds");
            } else if (KEY_BIND.equals(LAST_KEY_BIND) && TIME.getElapsedSeconds() >= DURATION) {
                System.out.println("Recorded " + KEY_BIND);
                return KEY_BIND;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
