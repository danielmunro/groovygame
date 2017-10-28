package com.groovygame.map.provider

import com.groovygame.mob.Mob
import com.groovygame.mob.Patrol
import com.groovygame.pathfinding.LayerSearch
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
                            coords: Coords.at(7, 7),
                            patrol: new Patrol(
                                    src: Coords.at(7, 7),
                                    dest: Coords.at(3, 7),
                                    path: new LayerSearch(blockingLayer).find(Coords.at(7,7), Coords.at(3, 7))
                            )
                    )
            ]
        )
    }
}
