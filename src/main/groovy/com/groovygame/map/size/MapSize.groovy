package com.groovygame.map.size

import com.groovygame.map.topology.MapTopology

interface MapSize {
    int getWidth()
    int getHeight()
    int getRandomMountainPeakCountForTopology(MapTopology topology)
}