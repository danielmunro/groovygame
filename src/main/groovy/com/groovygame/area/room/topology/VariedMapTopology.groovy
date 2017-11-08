package com.groovygame.area.room.topology

import com.groovygame.area.room.tile.Terrain
import groovy.transform.Immutable

@Immutable
class VariedMapTopology implements MapTopology {
    int getMountainPeakModifier() {
        return 1
    }

    Terrain getTopology() {
        Terrain.HILLS
    }
}
