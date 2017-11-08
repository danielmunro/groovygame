package com.groovygame.game

import com.groovygame.image.ImageProvider
import com.groovygame.area.room.Layer
import com.groovygame.area.room.Map
import com.groovygame.mob.Projectile
import com.groovygame.ui.Sprite
import com.groovygame.util.Coords
import spock.lang.Specification

import javax.imageio.ImageIO

class ServiceSpec extends Specification {
    def "added projectiles decay"() {
        setup:
        def service = new Service(map: new Map(
                blocking: new Layer(data: [[0, 0, 0], [0, 0, 0], [0, 0, 0]])
        ))
        service.addProjectile(
                new Projectile(
                        decay: 1,
                        coords: Coords.at(0, 0),
                        image: new ImageProvider(
                                sprite: new Sprite(image: ImageIO.read(new File("sprites.png")))
                        ).getProjectileImage()
                )
        )

        when:
        service.addMilliseconds(1)

        then:
        service.getProjectileCount() == 1

        when:
        service.addMilliseconds(5)

        then:
        service.getProjectileCount() == 0
    }
}
