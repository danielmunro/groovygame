package com.groovygame.matrix

import com.groovygame.area.room.size.LargeMapSize
import com.groovygame.area.room.size.SmallMapSize
import com.groovygame.area.room.tile.Climate
import com.groovygame.area.room.tile.ResourceLevel
import com.groovygame.area.room.topology.FlatMapTopology
import com.groovygame.area.room.topology.MountainousMapTopology
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
