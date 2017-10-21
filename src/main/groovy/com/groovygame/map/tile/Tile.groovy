package com.groovygame.map.tile

class Tile {
    private Terrain terrain
    private Climate climate
    private Resource resource

    Tile(Terrain terrain, Climate climate, Resource resource) {
        this.terrain = terrain
        this.climate = climate
        this.resource = resource
    }

    Tile(Terrain terrain, Climate climate) {
        this.terrain = terrain
        this.climate = climate
    }

    Terrain getTerrain() {
        terrain
    }

    static Tile getMountainPeakTile() {
        new Tile(Terrain.MOUNTAIN_PEAK, Climate.TEMPERATE)
    }
}
