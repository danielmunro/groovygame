package com.groovygame.area.room.tile

import groovy.transform.Immutable

@Immutable
class Tile {
    private Terrain terrain
    private Climate climate
    private Resource resource

    Terrain getTerrain() {
        terrain
    }

    static Tile getMountainPeakTile() {
        new Tile(terrain: Terrain.MOUNTAIN_PEAK, climate: Climate.TEMPERATE)
    }
}
