package com.viveka01.copymail;

import java.io.ObjectInputFilter.Config;

import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void test() {
        ConfigManager configWriter = ConfigManager.getInstance();
        DefaultInputReturnStructure input = ConfigInput.defaultInput();
        configWriter.configFill(input.getSenderEmail(), input.getReceiverEmail(), input.getAppPassword(),
                input.getHotKey());
    }
}
