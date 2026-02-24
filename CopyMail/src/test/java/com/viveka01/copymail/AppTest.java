package com.viveka01.copymail;

import java.io.ObjectInputFilter.Config;

import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void test() {
        System.out.println(ClipBoardReader.readClipBoard());
    }
}
