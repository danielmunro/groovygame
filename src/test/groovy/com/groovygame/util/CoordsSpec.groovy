package com.groovygame.util

import spock.lang.Specification

class CoordsSpec extends Specification {
    def "sanity test coords equals"() {
        expect:
        new Coords(1, 1) == new Coords(1, 1)
        new Coords(1, 1) != new Coords(2, 1)
    }

    def "the distance between two points should be calculated correctly"() {
        expect:
        new Coords(0, 0).distanceFrom(new Coords(6, 8)) == 10
        new Coords(-2, -3).distanceFrom(new Coords(-4, 4)) == 7.280109889280518
        new Coords(-6, -3).distanceFrom(new Coords(10, 8)) == 19.4164878389476
    }
}
