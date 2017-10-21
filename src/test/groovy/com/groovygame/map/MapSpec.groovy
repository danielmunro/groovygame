package com.groovygame.map

import com.groovygame.Constants
import com.groovygame.mobile.player.Player
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
        def startCoords = new Coords(Constants.TILE_SIZE, Constants.TILE_SIZE)
        def player = new Player(map: map, coords: startCoords)

        when:
        player.moveRight()

        then:
        player.getCoords() == startCoords
    }
}
