package com.groovygame.map.topology

import com.groovygame.map.tile.Terrain
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
