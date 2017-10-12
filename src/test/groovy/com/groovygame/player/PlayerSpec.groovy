package com.groovygame.player

import com.groovygame.Constants
import com.groovygame.map.Map
import spock.lang.Specification

import static org.mockito.Mockito.*

class PlayerSpec extends Specification {
    Player player

    def setup() {
        player = new DemoPlayerProvider(mock(Map.class)).getPlayer()
    }

    def "should be able to move character down the screen"() {
        setup:
        def initialCoords = player.getCoords().clone()
        player.keyPressed(Constants.KEY_DOWN)

        when:
        player.move()

        then:
        player.getCoords() != initialCoords
    }

    def "releasing a movement key causes the character to stop moving"() {
        setup:
        player.keyPressed(Constants.KEY_DOWN)
        player.move()
        player.keyReleased(Constants.KEY_DOWN)
        def initialCoords = player.getCoords().clone()

        when:
        player.move()

        then:
        player.getCoords() == initialCoords
    }

    def "space bar should cause the character to attack"() {
        when:
        player.keyPressed(Constants.KEY_SPACE)

        then:
        player.isAttacking()
    }

    def "releasing space bar causes the character to stop attacking"() {
        setup:
        player.keyPressed(Constants.KEY_SPACE)

        when:
        player.keyReleased(Constants.KEY_SPACE)

        then:
        !player.isAttacking()
    }

    def "a player should be able to create identical projectile copies"() {
        when:
        def projectile = player.getNewProjectile()

        then:
        projectile == player.getNewProjectile()
    }
}
