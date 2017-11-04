package com.groovygame.mob.player

import com.groovygame.animation.BlueExplosionAnimationProvider
import com.groovygame.game.Service
import com.groovygame.image.ImageProvider
import com.groovygame.util.Coords
import com.groovygame.util.Direction
import com.groovygame.mob.Projectile
import com.groovygame.ui.Sprite

import javax.imageio.ImageIO

class DemoPlayerProvider implements PlayerProvider {
    Service service

    Player getPlayer() {
        def sprite = new Sprite(image: ImageIO.read(new File("sprites.png")))
        return new Player(
                service: service,
                coords: new Coords(100, 100),
                image: sprite.getImageAtCoords(new Coords(1, 2)),
                projectile: new Projectile(
                        damage: 1,
                        speed: 2,
                        decay: 50,
                        direction: Direction.RIGHT,
                        coords: new Coords(100, 100),
                        image: new ImageProvider(sprite: sprite).getProjectileImage(),
                        explosionImages: new BlueExplosionAnimationProvider(sprite).getAnimationFrames()
                )
        )
    }
}
