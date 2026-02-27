package com.viveka01.copymail;

import java.io.ObjectInputFilter.Config;

import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void test() {
        ConfigManager configWriter = ConfigManager.getInstance();
        DefaultInputReturnStructure input = ConfigInput.DefaultInputReturn();
        configWriter.configFill(input.getSenderEmail(),);
    }
}
