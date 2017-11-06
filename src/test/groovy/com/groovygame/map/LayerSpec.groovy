package com.groovygame.map

import com.groovygame.util.Coords
import spock.lang.Specification

class LayerSpec extends Specification {
    def "coords too big for layer should be out of bounds"() {
        expect:
        !new Layer(data: [[]]).coordsInBounds(new Coords(1, 1))
        !new Layer(data: [[0]]).coordsInBounds(new Coords(0, 1))
        !new Layer(data: [[0]]).coordsInBounds(new Coords(1, 0))
        !new Layer(data: [[0]]).coordsInBounds(new Coords(-1, 0))
        !new Layer(data: [[0]]).coordsInBounds(new Coords(0, -1))
        def layer = new Layer(data: [[0, 0, 0], [0, 0, 0], [0, 0, 0]])
        !layer.coordsInBounds(new Coords(10, 10))
        !layer.coordsInBounds(new Coords(-1, 0))
        !layer.coordsInBounds(new Coords(11, 1))
        !layer.coordsInBounds(new Coords(5, 10))
    }

    def "negative coords should be out of bounds"() {
        expect:
        !new Layer(data: [[]]).coordsInBounds(new Coords(-1, 0))
        !new Layer(data: [[]]).coordsInBounds(new Coords(0, -1))
        !new Layer(data: [[]]).coordsInBounds(new Coords(-1, -1))
    }

    def "coordinates that lie within the layer should be in bounds"() {
        expect:
        new Layer(data: [[0]]).coordsInBounds(new Coords(0, 0))
        def layer = new Layer(data: [[0, 0, 0], [0, 0, 0], [0, 0, 0]])
        layer.coordsInBounds(new Coords(0, 0))
        layer.coordsInBounds(new Coords(2, 2))
        layer.coordsInBounds(new Coords(2, 0))
        layer.coordsInBounds(new Coords(0, 2))
        layer.coordsInBounds(new Coords(1, 1))
    }

    def "ensure width/height sanity"() {
        setup:
        def layer = new Layer(
                data: [
                        [0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0],
                        [0, 0, 0, 0, 0]
                ]
        )

        expect:
        layer.width() == 5
        layer.height() == 3
    }
}
