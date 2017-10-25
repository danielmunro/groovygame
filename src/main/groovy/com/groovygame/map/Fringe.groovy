package com.groovygame.map

import com.groovygame.util.Coords

class Fringe {
    ArrayList<Coords> coords = []

    void addCoords(Coords coords) {
        this.coords << coords
    }

    Coords[] getCoords() {
        this.coords.clone()
    }

    int size() {
        this.coords.size()
    }
}
