package com.groovygame.game

import com.groovygame.image.ImageProvider
import com.groovygame.map.Layer
import com.groovygame.map.Map
import com.groovygame.mob.Projectile
import com.groovygame.ui.Sprite
import com.groovygame.util.Constants
import com.groovygame.util.Coords
import spock.lang.Specification

import javax.imageio.ImageIO
import java.awt.Rectangle

class ServiceSpec extends Specification {
    def "service checks map for proper blocking data"() {
        setup:
        def service = new Service(map: new Map(
                blocking: new Layer(data: [[1, 1, 1], [1, 1, 1], [1, 1, 1]])
        ))

        expect:
        service.isMapBlocking(new Rectangle(0, 0, Constants.TILE_SIZE, Constants.TILE_SIZE))
    }

    def "added projectiles decay"() {
        setup:
        def service = new Service(map: new Map(
                blocking: new Layer(data: [[0, 0, 0], [0, 0, 0], [0, 0, 0]])
        ))
        def sprite = new Sprite(image: ImageIO.read(new File("sprites.png")))
        service.addProjectile(new Projectile(decay: 1, coords: Coords.at(0, 0), image: new ImageProvider(sprite:sprite).getProjectileImage()))

        when:
        service.addMilliseconds(1)

        then:
        service.getProjectiles().size() == 1

        when:
        service.addMilliseconds(5)

        then:
        service.getProjectiles().size() == 0
    }
}
