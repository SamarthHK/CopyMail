package com.viveka01.copymail;

public class Timer {

    private long startTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public void reset() {
        start(); // same as starting again
    }

    public long getElapsedNanos() {
        return System.nanoTime() - startTime;
    }

    public long getElapsedMillis() {
        return getElapsedNanos() / 1_000_000;
    }

    public double getElapsedSeconds() {
        return getElapsedNanos() / 1_000_000_000;
    }
}
