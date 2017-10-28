package com.groovygame.mob.player

import com.groovygame.util.Coords
import com.groovygame.util.Direction
import com.groovygame.map.Map
import com.groovygame.mob.Projectile
import com.groovygame.ui.Sprite

import javax.imageio.ImageIO

class DemoPlayerProvider implements PlayerProvider {
    Map map

    Player getPlayer() {
        def sprite = new Sprite(image: ImageIO.read(new File("sprites.png")))
        return new Player(
                map: map,
                coords: new Coords(100, 100),
                image: sprite.getImageAtCoords(new Coords(1, 2)),
                cooldown: 200,
                projectile: new Projectile(
                        damage: 1,
                        speed: 2,
                        decay: 500,
                        direction: Direction.RIGHT,
                        coords: new Coords(100, 100),
                        image: sprite.getImageAtCoords(
                                new Coords(238, 1284),
                                4,
                                1
                        )
                )
        )
    }
}
