package com.viveka01.copymail;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KeyListener implements NativeKeyListener {
    // Creating a static object of this class, so it starts recording
    private static final KeyListener INSTANCE = new KeyListener(); // singleton pattern
    private static final Map<String, Boolean> keyStates = new ConcurrentHashMap<>();

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        String KEY_NAME = NativeKeyEvent.getKeyText(e.getKeyCode());
        keyStates.put(KEY_NAME, true);
        System.out.println("Pressed: " + KEY_NAME);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        String KEY_NAME = NativeKeyEvent.getKeyText(e.getKeyCode());
        keyStates.put(KEY_NAME, false);
        System.out.println("Released: " + KEY_NAME);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        String KEY_NAME = NativeKeyEvent.getKeyText(e.getKeyCode());
        System.out.println("Typed: " + KEY_NAME);
    }

    /**
     * Check if a key is currently pressed.
     * 
     * @param keyName The key name, e.g., "A", "Shift", "Alt"
     * @return true if pressed, false otherwise
     */
    protected boolean isKeyPressed(String KEY_NAME) {
        Boolean OUTPUT = keyStates.getOrDefault(KEY_NAME, false);
        if (OUTPUT) {
            System.out.println("Specified key pressed: " + KEY_NAME);
        }
        return OUTPUT;
    }

    protected static KeyListener getInstance() {
        System.out.println("Instance Called");
        return INSTANCE;
    }

    // Starts recording the screen
    private KeyListener() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("Listener Init");
        GlobalScreen.addNativeKeyListener(this);
    }

}
