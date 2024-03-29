package com.groovygame.matrix

import com.groovygame.area.room.tile.Climate

import com.groovygame.area.room.tile.ResourceLevel
import com.groovygame.area.room.tile.Terrain
import com.groovygame.area.room.tile.Tile
import com.groovygame.area.room.topology.MapTopology
import com.groovygame.area.room.size.MapSize
import com.groovygame.util.Coords

class TileMatrix {
    private MapSize mapSize
    private MapTopology topology
    private ResourceLevel resourceLevel
    private Climate climate
    private Matrix matrix
    private Random random

    TileMatrix(MapSize mapSize, MapTopology topology, ResourceLevel resourceLevel, Climate climate) {
        this.mapSize = mapSize
        this.topology = topology
        this.resourceLevel = resourceLevel
        this.climate = climate
        matrix = new Matrix(mapSize.getWidth(), mapSize.getHeight(), {new Tile(terrain: Terrain.PLAINS, climate: Climate.TEMPERATE)})
        random = new Random()
        buildTileMatrix()
    }

    private void buildTileMatrix() {
        buildMountainPeaks()
    }

    private void buildMountainPeaks() {
        getRandomMountainPeakCount().times{
            matrix.setValueAtCoords(getRandomCoords(), Tile.getMountainPeakTile())
        }
    }

    private Coords getRandomCoords() {
        new Coords(random.nextInt(mapSize.getWidth()), random.nextInt(mapSize.getHeight()))
    }

    private int getRandomMountainPeakCount() {
        mapSize.getRandomMountainPeakCountForTopology(topology)
    }

    def findAllInMatrix(Closure c) {
        matrix.getMatrix().findAll{
            it.findAll{
                if (c(it)) {
                    it
                }
            }
        }
    }
}
