package com.groovygame.area.room.size

import com.groovygame.area.room.topology.MapTopology
import com.groovygame.util.Random

class MediumMapSize implements MapSize {
    int getWidth() {
        500
    }

    int getHeight() {
        500
    }

    int getRandomMountainPeakCountForTopology(MapTopology topology) {
        Random.dice(2, topology.getMountainPeakModifier())
    }
}
