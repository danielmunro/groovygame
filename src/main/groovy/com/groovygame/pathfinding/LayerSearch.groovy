package com.groovygame.pathfinding

import com.groovygame.map.Layer
import com.groovygame.map.exception.ImpossiblePathException
import com.groovygame.util.Coords

class LayerSearch {
    private Layer layer
    private Coords src
    private Coords dest
    private FringeCollection collection

    LayerSearch(Layer layer) {
        this.layer = layer
    }

    def find(Coords src, Coords dest) {
        this.src = src
        this.dest = dest
        collection = new FringeCollection(src)
        buildFringeStructure()
        findPath(src, getInitialPath(), 1)
    }

    private boolean isDepthAtEdgeOfFringe(int depth) {
        collection.getFringeSize() == depth
    }

    private getInitialPath() {
        [src]
    }

    private List<Coords> findPath(Coords current, List<Coords> currentPath, int currentDepth) {
        if (collection.getFringeSize() == currentDepth) {
            return
        }
        for (Coords it in collection.getCoordsAtDepth(currentDepth)) {
            if (current.isNeighbor(it)) {
                if (it == dest) {
                    currentPath << it
                    return currentPath
                }
                def completePath = findPath(it, newPathFromCurrentPath(currentPath, it), currentDepth + 1)
                if (completePath) {
                    return completePath
                }
            }
        }
    }

    private static newPathFromCurrentPath(def currentPath, Coords coords) {
        def newPath = (ArrayList<Coords>) currentPath.clone()
        newPath << coords
        newPath
    }

    private void buildFringeStructure() {
        int depth = 0
        while (!collection.fringesBuilt()) {
            if (isDepthAtEdgeOfFringe(depth)) {
                throw new ImpossiblePathException()
            }
            buildFringe(depth)
            depth++
        }
    }

    private buildFringe(int depth) {
        collection.getCoordsAtDepth(depth).each{
            getValidDistanceSortedNeighborCoords(it).each{
                considerAddingCoordsToFringe(depth+1, it)
            }
        }
    }

    private considerAddingCoordsToFringe(int depth, Coords coords) {
        if (!collection.wasVisited(coords)) {
            if (isDepthAtEdgeOfFringe(depth)) {
                collection.increaseDepth()
            }
            collection.addCoordsAtDepth(depth, coords)
            if (coords == dest) {
                collection.destinationLocated()
            }
            collection.addVisited(coords)
        }
    }

    private getValidDistanceSortedNeighborCoords(Coords origin) {
        [
                new Coords(origin.getX()-1, origin.getY()),
                new Coords(origin.getX(), origin.getY()-1),
                new Coords(origin.getX()+1, origin.getY()),
                new Coords(origin.getX(), origin.getY()+1),
        ]
        .findAll{
            !collection.wasVisited(it) && layer.areCoordsWalkable(it)
        }
        .sort{ Coords a, Coords b ->
            src.distanceFrom(a) - src.distanceFrom(b)
        }
    }
}
