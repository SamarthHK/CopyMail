package com.viveka01.copymail;
//java -cp target/classes com.viveka01.copymail.App

public class App {
    public static void main(String[] args) {
        KeyListener READER = KeyListener.getInstance();
        while (!READER.isKeyPressed("Escape")) {
            if (READER.isKeyPressed("A")) {
                System.out.println("Key Pressed");
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
