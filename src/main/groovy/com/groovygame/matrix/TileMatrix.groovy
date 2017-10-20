package com.groovygame.matrix

import com.groovygame.map.Climate

import com.groovygame.map.ResourceLevel
import com.groovygame.map.Terrain
import com.groovygame.map.Tile
import com.groovygame.map.topology.MapTopology
import com.groovygame.map.size.MapSize
import com.groovygame.util.Coords

import groovy.json.JsonBuilder

class TileMatrix {
    private MapSize mapSize
    private MapTopology topology
    private ResourceLevel resourceLevel
    private Climate climate
    private Matrix matrix

    TileMatrix(MapSize mapSize, MapTopology topology, ResourceLevel resourceLevel, Climate climate) {
        this.mapSize = mapSize
        this.topology = topology
        this.resourceLevel = resourceLevel
        this.climate = climate
        matrix = new Matrix(mapSize.getWidth(), mapSize.getHeight())
        buildTileMatrix()
    }

    private void buildTileMatrix() {
        buildMountainPeaks()
        while (loopBuild()) {}
    }

    private boolean loopBuild() {
        def valuesSet = 0
        for (def y = 0; y < matrix.getHeight(); y++) {
            for (def x = 0; x < matrix.getWidth(); x++) {
                Coords coords = new Coords(x, y)
                if (!matrix.getValueAtCoords(coords) && attemptToFillCoords(coords)) {
                    valuesSet++
                }
            }
        }

        valuesSet > 0
    }

    private boolean attemptToFillCoords(Coords coords) {
        def tile = getHintingTile(new Coords(coords.getX() + 1, coords.getY()))
        if (!tile) {
            matrix.setValueAtCoords(coords, new Tile(Terrain.PLAINS, Climate.TEMPERATE))

            true
        }

        false
    }

    private void getHintingTile(Coords coords) {

    }

    private void buildMountainPeaks() {
        def random = new Random()
        (0..getRandomMountainPeakCount()).each{
            matrix.setValueAtCoords(
                    new Coords(
                            random.nextInt(mapSize.getWidth()),
                            random.nextInt(mapSize.getHeight())
                    ),
                    new Tile(
                            Terrain.MOUNTAIN_PEAK,
                             Climate.TEMPERATE
                    )
            )
        }
    }

    private int getRandomMountainPeakCount() {
        mapSize.getRandomMountainPeakCountForTopology(topology)
    }

    @Override
    String toString() {
        new JsonBuilder(matrix).toString()
    }

    List findAllInMatrix(Closure c) {
        def tiles = []
        for (rows in matrix.getMatrix()) {
            for (col in rows.getValue()) {
                Tile t = col.getValue()
                if (c(t)) {
                    tiles.push(t)
                }
            }
        }
        tiles
    }
}
