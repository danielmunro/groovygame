package com.groovygame.map.size

import com.groovygame.map.topology.MapTopology
import com.groovygame.util.Random

class MediumMapSize implements MapSize {
    int getWidth() {
        500
    }

    int getHeight() {
        500
    }

    int getRandomMountainPeakCountForTopology(MapTopology topology) {
        Random.dice(6 * topology.getMountainPeakModifier())
    }
}
