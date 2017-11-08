package com.groovygame.area.room

import com.groovygame.game.Service
import com.groovygame.mob.Direction
import com.groovygame.mob.Mob
import com.groovygame.util.Constants
import com.groovygame.util.Coords
import spock.lang.Specification

class MapSpec extends Specification {
    def "blocking data blocks mob movement"() {
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
        def mob = new Mob(
                coords: startCoords,
                service: service
        )

        when:
        mob.moveTo(new Coords(mob.getCoords().getX()+1, mob.getCoords().getY()), Direction.RIGHT)

        then:
        mob.getCoords() == startCoords
    }
}
