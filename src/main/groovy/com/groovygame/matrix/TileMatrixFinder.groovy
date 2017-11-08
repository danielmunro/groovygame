package com.groovygame.matrix

import com.groovygame.area.room.tile.Terrain
import groovy.transform.Immutable

@Immutable
class TileMatrixFinder {
    static findAllMountainPeaks(TileMatrix tileMatrix) {
        tileMatrix.findAllInMatrix{
            it.getTerrain() == Terrain.MOUNTAIN_PEAK
        }
    }
}
