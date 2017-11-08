package com.groovygame.area.room

import com.groovygame.util.Coords
import com.sun.javaws.exceptions.InvalidArgumentException

import groovy.transform.Immutable

import java.awt.image.BufferedImage

@Immutable
class Layer {
    private def data = []
    private BufferedImage[] tiles = []

    void forEachTile(Closure closure) {
        data.eachWithIndex { def row, int y ->
            row.eachWithIndex { int i, int x ->
                if (i > 0) {
                    closure(new Coords(x, y))
                }
            }
        }
    }

    def find(Closure closure) {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (closure(new Coords(x, y), data[y][x])) {
                    return true
                }
            }
        }
    }

    BufferedImage getTileAtCoords(Coords coords) {
        if (!coordsInBounds(coords)) {
            throw new InvalidArgumentException('coordinates must be in bounds')
        }

        tiles[data[coords.getY()][coords.getX()]-1]
    }

    int width() {
        data[0].size()
    }

    int height() {
        data.size()
    }

    boolean areCoordsWalkable(Coords coords) {
        coordsInBounds(coords) && isNotBlocked(coords)
    }

    boolean isNotBlocked(Coords coords) {
        return data[coords.getY()][coords.getX()] == 0
    }

    boolean coordsInBounds(Coords coords) {
        coords.getX() >= 0 &&
                coords.getX() <= width()-1 &&
                coords.getY() >= 0 &&
                coords.getY() <= height()-1
    }
}
