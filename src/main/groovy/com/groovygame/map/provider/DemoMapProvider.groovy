package com.groovygame.map.provider

import com.groovygame.mob.Mob
import com.groovygame.mob.Patrol
import com.groovygame.pathfinding.LayerSearch
import com.groovygame.util.Constants
import com.groovygame.util.Coords
import com.groovygame.map.Layer
import com.groovygame.map.Map
import com.groovygame.ui.Sprite
import groovy.transform.Immutable

import javax.imageio.ImageIO

@Immutable
class DemoMapProvider implements MapProvider {
    Map getMap() {
        def sprite = new Sprite(image: ImageIO.read(new File("sprites.png")))
        def mobSrcCoords = new Coords(7, 7)
        def blockingLayer = new Layer(
                data: [
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                        [0, 0, 1, 1, 1, 0, 0, 0, 0, 1],
                        [0, 0, 0, 0, 1, 0, 0, 0, 1, 1],
                        [0, 0, 0, 0, 1, 0, 0, 0, 1, 0],
                ],
                tiles: [
                    sprite.getImageAtCoords(new Coords(0, 14))
                ]
            )
        new Map(
            background: new Layer(
                data: [
                    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
                ],
                tiles: [
                    sprite.getImageAtCoords(new Coords(0, 15))
                ]
            ),
            blocking: blockingLayer,
            mobs: [
                    new Mob(
                            image: sprite.getImageAtCoords(Coords.at(0, 3)),
                            coords: mobSrcCoords,
                            patrol: new Patrol(
                                    path: new LayerSearch(blockingLayer).scaleAndFind(
                                            (1/Constants.TILE_SIZE),
                                            mobSrcCoords,
                                            Coords.at(3 * Constants.TILE_SIZE, 7 * Constants.TILE_SIZE)
                                    )
                            )
                    )
            ]
        )
    }
}
