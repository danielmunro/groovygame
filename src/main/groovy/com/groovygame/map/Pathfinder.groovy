package com.groovygame.map

import com.groovygame.map.exception.ImpossiblePathException
import com.groovygame.util.Coords

class Pathfinder {
    private Layer blockingLayer
    private ArrayList<Fringe> fringes = new ArrayList<Fringe>()
    private boolean fringesBuilt = false
    private Coords src
    private def visited = []

    Pathfinder(Layer blockingLayer) {
        this.blockingLayer = blockingLayer
    }

    def find(Coords src, Coords dest) {
        this.src = src
        resetSearch()
        int depth = 0
        while(!fringesBuilt) {
            if (fringes.size() == depth) {
                throw new ImpossiblePathException()
            }
            buildFringe(dest, depth)
            depth++
        }
        findPath(src, dest, getInitialPath(), 1)
    }

    private void resetSearch() {
        fringes = [new Fringe(getInitialPath())]
        fringesBuilt = false
        visited = []
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
                    return currentPath
                }
                def newPath = newPathFromCurrentPath(currentPath)
                newPath << it
                def completePath = findPath(it, dest, newPath, currentDepth + 1)
                if (completePath) {
                    return completePath
                }
            }
        }
    }

    private static ArrayList<Coords> newPathFromCurrentPath(ArrayList<Coords> currentPath) {
        (ArrayList<Coords>) currentPath.clone()
    }

    private buildFringe(Coords dest, int depth) {
        fringes[depth].getCoords().each{
            neighborCoords(it).each{
                considerAddingCoordsToFringe(depth+1, it, dest)
            }
        }
    }

    private considerAddingCoordsToFringe(int depth, Coords coords, Coords dest) {
        if (!visited.contains(coords)) {
            if (fringes.size() == depth) {
                fringes << new Fringe()
            }
            fringes[depth].addCoords(coords)
            if (coords == dest) {
                fringesBuilt = true
            }
            visited << coords
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
            !visited.contains(it) && blockingLayer.areCoordsWalkable(it)
        }
        .sort{ Coords a, Coords b ->
            src.distanceFrom(a) - src.distanceFrom(b)
        }
    }
}
