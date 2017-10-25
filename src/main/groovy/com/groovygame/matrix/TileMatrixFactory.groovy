package com.groovygame.matrix

import com.groovygame.map.size.LargeMapSize
import com.groovygame.map.size.SmallMapSize
import com.groovygame.map.tile.Climate
import com.groovygame.map.tile.ResourceLevel
import com.groovygame.map.topology.FlatMapTopology
import com.groovygame.map.topology.MountainousMapTopology
import groovy.transform.Immutable

@Immutable
class TileMatrixFactory {
    static TileMatrix getNewSmallFlatMap() {
        new TileMatrix(
                new SmallMapSize(),
                new FlatMapTopology(),
                ResourceLevel.LOW,
                Climate.TEMPERATE
        )
    }

    static TileMatrix getNewLargeMountainousMap() {
        new TileMatrix(
                new LargeMapSize(),
                new MountainousMapTopology(),
                ResourceLevel.LOW,
                Climate.TEMPERATE
        )
    }
}
