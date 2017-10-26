package com.groovygame.util

import groovy.transform.Immutable

@Immutable
class Coords {
    int x
    int y

    static at(int x, int y) {
        new Coords(x: x, y: y)
    }

    boolean isNeighbor(Coords c) {
        def xdiff = Math.abs(this.x - c.getX())
        def ydiff = Math.abs(this.y - c.getY())

        xdiff == 1 && ydiff == 0 || xdiff == 0 && ydiff == 1
    }

    def distanceFrom(Coords c) {
        return Math.sqrt(Math.pow(x - c.getX(), 2) + Math.pow(y - c.getY(), 2))
    }

    @Override
    String toString() {
        "(" + x + ", " + y + ")"
    }
}
