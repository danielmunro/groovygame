package com.groovygame.util

class Coords {
    private int x
    private int y

    Coords(int x, int y) {
        this.x = x
        this.y = y
    }

    int getX() {
        x
    }

    int getY() {
        y
    }

    Coords clone() {
        new Coords(x, y)
    }

    boolean equals(Coords c) {
        x == c.getX() && y == c.getY()
    }

    @Override
    String toString() {
        "(" + x + ", " + y + ")"
    }
}
