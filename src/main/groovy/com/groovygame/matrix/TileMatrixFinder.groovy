package com.groovygame.matrix

import com.groovygame.map.tile.Terrain

class TileMatrixFinder {
    static findAllMountainPeaks(TileMatrix tileMatrix) {
        tileMatrix.findAllInMatrix{
            it.getTerrain() == Terrain.MOUNTAIN_PEAK
        }
    }
}
