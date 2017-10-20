package com.groovygame.map.size

import com.groovygame.map.topology.MapTopology
import com.groovygame.util.Random

class LargeMapSize implements MapSize {
    int getWidth() {
        1000
    }

    int getHeight() {
        1000
    }

    int getRandomMountainPeakCountForTopology(MapTopology topology) {
        Random.dice(3, topology.getMountainPeakModifier())
    }
}

