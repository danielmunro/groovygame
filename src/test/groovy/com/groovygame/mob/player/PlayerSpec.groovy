package com.groovygame.mob.player

import com.groovygame.game.Service
import com.groovygame.util.Constants
import com.groovygame.area.room.Map
import spock.lang.Specification

import static org.mockito.Mockito.*

class PlayerSpec extends Specification {
    Player player
    Service service

    def setup() {
        service = new Service(map: mock(Map.class))
        player = new DemoPlayerProvider(service: service).getPlayer()
    }

    def "should be able to move character down the screen"() {
        setup:
        def initialCoords = player.getCoords()

        when:
        player.keyPressed(Constants.KEY_DOWN)
        player.move()
        player.keyReleased(Constants.KEY_DOWN)

        then:
        player.getCoords() != initialCoords
    }

    def "releasing a movement key causes the character to stop moving"() {
        setup:
        player.keyPressed(Constants.KEY_DOWN)
        player.move()
        player.keyReleased(Constants.KEY_DOWN)
        def initialCoords = player.getCoords()

        when:
        player.move()

        then:
        player.getCoords() == initialCoords
    }

    def "releasing space bar causes the character to stop attacking"() {
        expect:
        !player.isAttackKeyPressed()

        when:
        player.keyPressed(Constants.KEY_ATTACK)

        then:
        player.isAttackKeyPressed()

        when:
        player.keyReleased(Constants.KEY_ATTACK)

        then:
        !player.isAttackKeyPressed()
    }

    def "a player should be able to create identical projectile copies"() {
        when:
        def projectile = player.getNewProjectile()

        then:
        projectile == player.getNewProjectile()
    }

    def "a player should only be able to add unique key values once"() {
        setup:
        player.keyPressed(Constants.KEY_SPACE)

        when:
        player.keyPressed(Constants.KEY_SPACE)

        then:
        player.keysPressed().size() == 1
    }

    def "a player should ignore a key released event that they don't know about"() {
        setup:
        player.keyPressed(Constants.KEY_SPACE)

        when:
        player.keyReleased(Constants.KEY_SPACE)
        player.keyReleased(Constants.KEY_SPACE)

        then:
        player.keysPressed().size() == 0
        !player.isKeyPressed(Constants.KEY_SPACE)
    }

    def "a player should be able to shoot a projectile by hitting the attack key"() {
        expect:
        service.getProjectileCount() == 0

        when:
        player.keyPressed(Constants.KEY_ATTACK)

        then:
        service.getProjectileCount() == 1
    }
}
