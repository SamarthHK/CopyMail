package com.viveka01.copymail;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;
import java.util.HashSet;

public class KeyListener implements NativeKeyListener {
    // Creating a static object of this class, so it starts recording
    private static final KeyListener INSTANCE = new KeyListener(); // singleton pattern
    private static final Map<String, Boolean> KEY_STATES = new ConcurrentHashMap<>();
    private static final Map<Integer, HashSet<String>> REVERSE_KEY_STATE = new ConcurrentHashMap<>();
    static {
        REVERSE_KEY_STATE.put(1, new HashSet<>());
        REVERSE_KEY_STATE.put(0, new HashSet<>());
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        String KEY_NAME = NativeKeyEvent.getKeyText(e.getKeyCode()).toLowerCase();
        KEY_STATES.put(KEY_NAME, true);
        REVERSE_KEY_STATE.get(1).add(KEY_NAME);
        REVERSE_KEY_STATE.get(0).remove(KEY_NAME);
        // System.out.println("Pressed: " + KEY_NAME);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        String KEY_NAME = NativeKeyEvent.getKeyText(e.getKeyCode()).toLowerCase();
        KEY_STATES.put(KEY_NAME, false);
        REVERSE_KEY_STATE.get(0).add(KEY_NAME);
        REVERSE_KEY_STATE.get(1).remove(KEY_NAME);
        // System.out.println("Released: " + KEY_NAME);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    /**
     * Check if a key is currently pressed.
     * 
     * @param keyName The key name, e.g., "A", "Shift", "Alt"
     * @return true if pressed, false otherwise
     */
    public boolean isKeyPressed(String KEY_NAME) {
        Boolean OUTPUT = KEY_STATES.getOrDefault(KEY_NAME.toLowerCase(), false);
        return OUTPUT;
    }

    public boolean isKeyBindPressed(String keyBind) {
        String[] induvigualKeyBind = keyBind.split("\\+");
        Set<String> induvigualKeyBindSet = new HashSet<>(Arrays.asList(induvigualKeyBind));
        return isAnyKeyPressed().equals(induvigualKeyBindSet);
    }

    protected HashSet<String> isAnyKeyPressed() {
        return REVERSE_KEY_STATE.get(1);
    }

    public static KeyListener getInstance() {
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
