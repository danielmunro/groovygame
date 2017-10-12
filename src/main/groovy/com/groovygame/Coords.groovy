package com.groovygame

//import java.awt.Rectangle

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
        return new Coords(x, y)
    }

    boolean equals(Coords c) {
        return x == c.getX() && y == c.getY()
    }

    @Override
    String toString() {
        return "(" + x + ", " + y + ")"
    }

    /**
    Rectangle tileRectangle() {
        new Rectangle(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE)
    }
    */
}
