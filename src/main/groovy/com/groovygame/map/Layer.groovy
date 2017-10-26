package com.groovygame.map

import com.groovygame.util.Coords
import groovy.transform.Immutable

import java.awt.image.BufferedImage

@Immutable
class Layer {
    private int[][] data
    private BufferedImage[] tiles

    int[][] getData() {
        data
    }

    BufferedImage getTileFromIndex(int i) {
        tiles[i]
    }

    int width() {
        data[0].size()
    }

    int height() {
        data.length
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
