package com.groovygame.map

import com.groovygame.map.exception.ImpossiblePathException
import com.groovygame.util.Coords

class Pathfinder {
    private Layer blockingLayer
    private ArrayList<Fringe> fringes = new ArrayList<Fringe>()
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
        Fringe f = new Fringe()
        f.addCoords(src)
        fringes << f
        while(true) {
            def fringeSize = fringes.size()
            if (fringeSize > 0 && fringeSize == depth) {
                throw new ImpossiblePathException()
            }
            calculateFringes(dest, depth)
            depth++
            if (found) {
                break
            }
        }
        found = false
        findPath(src, dest, getInitialPath(), 1)
        path
    }

    private ArrayList<Coords> getInitialPath() {
        [src]
    }

    def findPath(Coords current, Coords dest, ArrayList<Coords> currentPath, int currentDepth) {
        if (fringes.size() == currentDepth) {
            return
        }
        for (Coords it in fringes[currentDepth].getCoords()) {
            if (current.isNeighbor(it)) {
                if (it == dest) {
                    currentPath << it
                    path = currentPath
                    found = true
                    return
                }
                def newPath = newPathFromCurrentPath(currentPath)
                newPath << it
                findPath(it, dest, newPath, currentDepth + 1)
                if (found) {
                    return
                }
            }
        }
    }

    private static ArrayList<Coords> newPathFromCurrentPath(ArrayList<Coords> currentPath) {
        (ArrayList<Coords>) currentPath.clone()
    }

    private calculateFringes(Coords dest, int depth) {
        fringes[depth].getCoords().each{ Coords it ->
            neighborCoords(it).each{ buildFringe(depth+1, it, dest) }
            visited << it
        }
    }

    private buildFringe(int depth, Coords coords, Coords dest) {
        if (!visited.contains(coords)) {
            if (fringes.size() == depth) {
                fringes << new Fringe()
            }
            fringes[depth].addCoords(coords)
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
        ]
        .findAll{
            !visited.contains(it) && coordsInBounds(it) && blockingLayer.isNotBlocked(it)
        }
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
