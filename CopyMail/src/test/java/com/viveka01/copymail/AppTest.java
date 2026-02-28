package com.viveka01.copymail;

import java.io.ObjectInputFilter.Config;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void test() {
        KeyListener reader = KeyListener.getInstance();
        System.out.println(Arrays.toString(reader.isKeyBindPressed("a+b+c+d")));
    }
}
