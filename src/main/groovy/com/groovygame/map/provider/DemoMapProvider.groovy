package com.groovygame.map.provider

import com.groovygame.image.ImageProvider
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
        def imageProvider = new ImageProvider(sprite: sprite)
        def mobSrcCoords = new Coords(7, 7)
        def mobDestCoords = new Coords(3, 7)
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
                            image: imageProvider.getHeroImage(),
                            coords: mobSrcCoords.scale(Constants.TILE_SIZE),
                            patrol: new Patrol(
                                    path: new LayerSearch(blockingLayer).find(
                                            mobSrcCoords,
                                            mobDestCoords
                                    )
                            )
                    )
            ]
        )
    }
}
