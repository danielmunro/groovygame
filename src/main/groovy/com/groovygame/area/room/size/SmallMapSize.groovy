package com.groovygame.area.room.size

import com.groovygame.area.room.topology.MapTopology
import com.groovygame.util.Random

class SmallMapSize implements MapSize {
    int getWidth() {
        100
    }

    int getHeight() {
        100
    }

    int getRandomMountainPeakCountForTopology(MapTopology topology) {
        Random.dice(2, topology.getMountainPeakModifier())
    }
}
