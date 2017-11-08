package com.groovygame.area.room

import com.groovygame.mob.player.Player
import com.groovygame.util.Coords
import spock.lang.Specification
import sun.plugin.dom.exception.InvalidStateException

class GateSpec extends Specification {
    def srcMap = new Map()
    def srcCoords = new Coords(1, 1)
    def destMap = new Map()
    def destCoords = new Coords(5, 5)
    def player = new Player(coords: new Coords(2, 1))
    def gate = new Gate(
            srcCoords: srcCoords,
            srcMap: srcMap,
            destCoords: destCoords,
            destMap: destMap
    )

    def "when a mob enters a gate they should be transported to the gate's destination"() {
        expect:
        !gate.isReadyToTransport(player)

        when:
        player.setCoords(srcCoords)

        then:
        gate.isReadyToTransport(player)

        when:
        gate.transport(player)

        then:
        player.getCoords() == destCoords
    }

    def "when a mob is not at source and tries to transport, an exception happens"() {
        when:
        gate.transport(player)

        then:
        InvalidStateException e = thrown()
    }
}
