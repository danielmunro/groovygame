package com.groovygame.map

import com.groovygame.util.Coords

class Pathfinder {
    private Layer blockingLayer
    private java.util.Map fringes = [:]
    private boolean found
    private Coords src
    private def visited = []
    private def path = []

    Pathfinder(Layer blockingLayer) {
        this.blockingLayer = blockingLayer
    }

    def find(Coords src, Coords dest) {
        this.src = src
        int depth = 0
        fringes = [0:[src]]
        while(true) {
            fringes[depth+1] = []
            if (fringes[depth].size() == 0) {
                return path
            }
            calculateFringes(dest, depth)
            depth++
            if (found) {
                break
            }
        }
        found = false
        findPath(src, dest, [src], 1)
        if (!found) {
            // error?
        }
        path
    }

    def findPath(Coords current, Coords dest, ArrayList<Coords> currentPath, int currentDepth) {
        for (Coords it in fringes[currentDepth]) {
            if (current.isNeighbor(it)) {
                if (it == dest) {
                    currentPath << it
                    path = currentPath
                    found = true
                    return
                }
                ArrayList<Coords> newPath = currentPath.clone()
                newPath << it
                findPath(it, dest, newPath, currentDepth + 1)
                if (found) {
                    return
                }
            }
        }
    }

    private calculateFringes(Coords dest, int depth) {
        fringes[depth].each{
            neighborCoords(it).each{ buildFringe(depth+1, it, dest) }
            visited << it
        }
    }

    private buildFringe(int depth, Coords coords, Coords dest) {
        if (!visited.contains(coords)) {
            fringes[depth] << coords
            if (coords == dest) {
                found = true
            }
        }
    }

    private Coords[] neighborCoords(Coords origin) {
        [
                new Coords(origin.getX()-1, origin.getY()),
                new Coords(origin.getX(), origin.getY()-1),
                new Coords(origin.getX()+1, origin.getY()),
                new Coords(origin.getX(), origin.getY()+1)
        ].findAll{!visited.contains(it) && coordsInBounds(it) && blockingLayer.isNotBlocked(it)}
        .sort{ Coords a, Coords b ->
            src.distanceFrom(a) - src.distanceFrom(b)
        }
    }

    private boolean coordsInBounds(Coords coords) {
        coords.getX() >= 0 &&
                coords.getX() <= blockingLayer.width()-1 &&
                coords.getY() >= 0 &&
                coords.getY() <= blockingLayer.height()-1
    }
}
