package com.groovygame.area.room

import com.groovygame.game.Service
import com.groovygame.mob.player.Player
import com.groovygame.util.Constants
import com.groovygame.util.Coords
import spock.lang.Specification

class MapSpec extends Specification {
    def "blocking data blocks player movement"() {
        setup:
        def map = new Map(
                blocking:
                        new Layer(
                                data: [
                                        [0, 0, 0],
                                        [0, 0, 1],
                                        [0, 0, 0]
                                ]
                        )
        )
        def service = new Service(map: map)
        def startCoords = Coords.at(Constants.TILE_SIZE, Constants.TILE_SIZE)
        def player = new Player(
                coords: startCoords,
                service: service
        )

        when:
        player.moveRight()

        then:
        player.getCoords() == startCoords
    }
}
