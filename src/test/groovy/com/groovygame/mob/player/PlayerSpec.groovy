package com.groovygame.mob.player

import com.groovygame.Constants
import com.groovygame.map.Map
import spock.lang.Specification

import static org.mockito.Mockito.*

class PlayerSpec extends Specification {
    Player player

    def setup() {
        player = new DemoPlayerProvider(map: mock(Map.class)).getPlayer()
    }

    def "should be able to move character down the screen"() {
        setup:
        def initialCoords = player.getCoords()

        when:
        player.moveDown()

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

    def "space bar should cause the character to attack"() {
        when:
        player.keyPressed(Constants.KEY_SPACE)

        then:
        player.isAttackKeyPressed()
    }

    def "releasing space bar causes the character to stop attacking"() {
        setup:
        player.keyPressed(Constants.KEY_SPACE)

        when:
        player.keyReleased(Constants.KEY_SPACE)

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
}
