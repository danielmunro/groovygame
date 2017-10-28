package com.groovygame.pathfinding

import com.groovygame.util.Coords

class FringeCollection {
    private List<Fringe> fringes = new ArrayList<Fringe>()
    private boolean fringesBuilt = false
    private def visited = []

    FringeCollection(Coords src) {
        fringes = [new Fringe([src])]
        fringesBuilt = false
        visited = []
    }

    void addCoordsAtDepth(int depth, Coords coords) {
        fringes[depth].addCoords(coords)
    }

    boolean fringesBuilt() {
        fringesBuilt
    }

    int getFringeSize() {
        fringes.size()
    }

    def getCoordsAtDepth(int depth) {
        fringes[depth].getCoords()
    }

    void increaseDepth() {
        fringes << new Fringe()
    }

    boolean wasVisited(Coords coords) {
        visited.contains(coords)
    }

    void addVisited(Coords coords) {
        visited << coords
    }

    void destinationLocated() {
        fringesBuilt = true
    }
}
