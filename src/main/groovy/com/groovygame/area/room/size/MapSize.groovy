package com.groovygame.area.room.size

import com.groovygame.area.room.topology.MapTopology

interface MapSize {
    int getWidth()
    int getHeight()
    int getRandomMountainPeakCountForTopology(MapTopology topology)
}