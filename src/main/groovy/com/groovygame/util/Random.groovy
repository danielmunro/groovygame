package com.groovygame.util

class Random {
    private static instance
    private java.util.Random random

    private Random() {
        random = new java.util.Random()
    }

    private static Random instance() {
        if (!instance) {
            instance = new Random()
        }

        instance
    }

    int getInt(int max) {
        random.nextInt(max)
    }

    static int dice(int roll, int dice) {
        (0..roll).inject{accumulator, i ->
            accumulator + instance().getInt(dice) + 1
        }
    }
}
