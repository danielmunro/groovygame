package com.groovygame.map.provider

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
            blocking: new Layer(
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
        )
    }
}
