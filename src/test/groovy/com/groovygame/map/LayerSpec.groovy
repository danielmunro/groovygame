package com.groovygame.map

import com.groovygame.util.Coords
import spock.lang.Specification

class LayerSpec extends Specification {
    def "coords too big for layer should be out of bounds"() {
        expect:
        !new Layer(data: [[]]).coordsInBounds(new Coords(1, 1))
        !new Layer(data: [[0, 0, 0], [0, 0, 0], [0, 0, 0]]).coordsInBounds(new Coords(10, 10))
    }

    def "negative coords should be out of bounds"() {
        expect:
        !new Layer(data: [[]]).coordsInBounds(new Coords(-1, 0))
        !new Layer(data: [[]]).coordsInBounds(new Coords(0, -1))
        !new Layer(data: [[]]).coordsInBounds(new Coords(-1, -1))
    }
}
