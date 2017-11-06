package com.groovygame.map

import com.groovygame.util.Coords
import com.sun.javaws.exceptions.InvalidArgumentException
import groovy.transform.Immutable

import java.awt.image.BufferedImage

@Immutable
class Layer {
    private def data = []
    private BufferedImage[] tiles = []

    def getData() {
        data
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
