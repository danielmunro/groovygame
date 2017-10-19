package com.groovygame.map.size

import com.groovygame.map.topology.MapTopology
import com.groovygame.util.Random

class SmallMapSize implements MapSize {
    int getWidth() {
        100
    }

    int getHeight() {
        100
    }

    int getRandomMountainPeakCountForTopology(MapTopology topology) {
        Random.dice(3 * topology.getMountainPeakModifier())
    }
}
