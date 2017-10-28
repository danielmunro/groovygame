package com.groovygame.pathfinding

import com.groovygame.util.Coords

class Fringe {
    List<Coords> coords = []

    Fringe(def coords) {
        this.coords = coords
    }

    Fringe() {
        this.coords = []
    }

    void addCoords(Coords coords) {
        this.coords << coords
    }

    def getCoords() {
        this.coords
    }

    int size() {
        this.coords.size()
    }
}
