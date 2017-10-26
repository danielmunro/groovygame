package com.groovygame.map

import com.groovygame.util.Coords

class Fringe {
    ArrayList<Coords> coords = []

    Fringe(ArrayList<Coords> coords) {
        this.coords = coords
    }

    Fringe() {
        this.coords = []
    }

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
